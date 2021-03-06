package com.emrekara.finalproject.app.product.service;

import com.emrekara.finalproject.app.gen.enums.GenErrorMessage;
import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.exceptions.BadRequestExceptions;
import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.product.converter.PrProductMapper;
import com.emrekara.finalproject.app.product.dto.*;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import com.emrekara.finalproject.app.product.enums.ProductErrorMessage;
import com.emrekara.finalproject.app.product.service.entityservice.PrProductEntityService;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrProductService {

    private final PrProductEntityService prProductEntityService;
    private final PrProductInfoEntityService prProductInfoEntityService;

    public PrProductDto save(PrProductSaveRequestDto prProductSaveRequestDto) {

        PrProduct prProduct = createSavePrProduct(prProductSaveRequestDto);

        prProduct = prProductEntityService.save(prProduct);

        PrProductDto prProductDto = PrProductMapper.INSTANCE.convertToPrProductDto(prProduct);

        return prProductDto;
    }

    public void delete(Long id) {

        PrProduct prProduct = prProductEntityService.getByIdWithControl(id);

        prProductEntityService.delete(prProduct);
    }


    public PrProductDto updatePrice(PrProductUpdatePriceDto prProductUpdatePriceDto) {

        Long id = prProductUpdatePriceDto.getId();

        controlIsProductExist(id);

        PrProduct prProduct = prProductEntityService.getByIdWithControl(id);

        BigDecimal vatRate = getVatRateFunc(prProduct.getProductType());

        BigDecimal vatFreePrice = prProductUpdatePriceDto.getVatFreePrice();
        validateVatFreePrice(vatFreePrice);

        BigDecimal vatPrice = calculateVatPrice(vatFreePrice, vatRate);
        BigDecimal finalPrice = calculateFinalPrice(vatFreePrice, vatPrice);

        prProduct.setVatPrice(vatPrice);
        prProduct.setFinalPrice(finalPrice);

        prProductEntityService.save(prProduct);

        PrProductDto prProductDto = PrProductMapper.INSTANCE.convertToPrProductDto(prProduct);

        return prProductDto;
    }

    public PrProductDto update(PrProductUpdateRequestDto prProductUpdateRequestDto) {
        controlIsProductExist(prProductUpdateRequestDto.getId());

        PrProductSaveRequestDto dto = PrProductMapper.INSTANCE.convertToPrProductSaveRequestDto(prProductUpdateRequestDto);
        PrProduct prProduct = createSavePrProduct(dto);
        prProduct.setId(prProductUpdateRequestDto.getId());
        prProduct = prProductEntityService.save(prProduct);

        PrProductDto prProductDto = PrProductMapper.INSTANCE.convertToPrProductDto(prProduct);

        return prProductDto;
    }

    public List<PrProductDto> findAll() {

        List<PrProduct> prProductList = prProductEntityService.findAll();

        List<PrProductDto> prProductDtoList = PrProductMapper.INSTANCE.convertToProductDtoList(prProductList);

        return prProductDtoList;
    }

    public List<PrProductDto> findAllByProduct(ProductType productType) {
        List<PrProduct> prProductList = prProductEntityService.findAllByProductType(productType);

        List<PrProductDto> prProductDtoList = PrProductMapper.INSTANCE.convertToProductDtoList(prProductList);

        return prProductDtoList;
    }

    public List<PrProductDto> findAllByFinalPriceBetween(BigDecimal minimum, BigDecimal maximum) {

        List<PrProduct> prProductList = prProductEntityService.findAllByFinalPriceBetween(minimum, maximum);

        List<PrProductDto> prProductDtoList = PrProductMapper.INSTANCE.convertToProductDtoList(prProductList);

        return prProductDtoList;
    }

    public PrProductDetailsDto findProductDetails(ProductType productType) {

        validateProductInfoType(productType);

        PrProductDetails productDetails = prProductEntityService.getProductDetails(productType);

        PrProductInfo prProductInfo = prProductInfoEntityService.findByProductType(productType);

        productDetails.setProductType(prProductInfo.getProductType());
        productDetails.setVatRate(prProductInfo.getVatRate());

        PrProductDetailsDto prProductDetailsDto = PrProductMapper.INSTANCE.convertToPrProductDetailsDto(productDetails);

        return prProductDetailsDto;
    }

    private PrProductValidatedParameters validatedParameters(PrProductSaveRequestDto prProductSaveRequestDto){

        String productName = prProductSaveRequestDto.getProductName();
        validateProductAttribute(productName.isEmpty(), ProductErrorMessage.PRODUCT_NAME_EMPTY_ERROR);

        ProductType productType = prProductSaveRequestDto.getProductType();
        validateProductAttribute(productType == null, ProductErrorMessage.PRODUCT_TYPE_EMPTY_ERROR);
        prProductInfoEntityService.validateProductTypeExist(productType);

        BigDecimal vatFreePrice = prProductSaveRequestDto.getVatFreePrice();
        validateVatFreePrice(vatFreePrice);
        PrProductValidatedParameters validatedParameters = new PrProductValidatedParameters();

        validatedParameters.setProductName(productName);
        validatedParameters.setProductType(productType);
        validatedParameters.setVatFreePrice(vatFreePrice);

        return validatedParameters;
    }

    private void validateProductAttribute(boolean productAttribute, ProductErrorMessage productNameEmptyError) {
        if(productAttribute){
            throw new BadRequestExceptions(productNameEmptyError);
        }
    }

    private BigDecimal calculateFinalPrice(BigDecimal vatFreePrice, BigDecimal vatPrice) {
        return vatFreePrice.add(vatPrice);
    }

    private BigDecimal calculateVatPrice(BigDecimal vatFreePrice, BigDecimal vatRate) {

        BigDecimal dividedVatRate = vatRate.divide(BigDecimal.valueOf(100));

        return vatFreePrice.multiply(dividedVatRate);
    }

    private PrProduct createSavePrProduct(PrProductSaveRequestDto dto) {
        PrProductValidatedParameters validatedParameters = validatedParameters(dto);

        BigDecimal vatFreePrice = validatedParameters.getVatFreePrice();
        ProductType productType = validatedParameters.getProductType();
        String productName = validatedParameters.getProductName();

        PrProductInfo prProductInfo = prProductInfoEntityService.findByProductType(productType);
        BigDecimal vatRate = prProductInfo.getVatRate();

        Long prProductInfoId = prProductInfo.getId();

        BigDecimal vatPrice = calculateVatPrice(vatFreePrice, vatRate);
        BigDecimal finalPrice = calculateFinalPrice(vatFreePrice, vatPrice);

        PrProduct prProduct = new PrProduct();
        prProduct.setProductName(productName);
        prProduct.setProductType(productType);
        prProduct.setVatFreePrice(vatFreePrice);
        prProduct.setProductInfoId(prProductInfoId);
        prProduct.setFinalPrice(finalPrice);
        prProduct.setVatPrice(vatPrice);

        return prProduct;
    }

    private void controlIsProductExist(Long id){

        boolean existsById = prProductEntityService.existsById(id);

        validateProductAttribute(!existsById, ProductErrorMessage.PRODUCT_NOT_FOUND_ERROR);
    }


    private BigDecimal getVatRateFunc(ProductType prProduct) {
        PrProductInfo productInfo = prProductInfoEntityService.findByProductType(prProduct);
        BigDecimal vatRate = productInfo.getVatRate();
        return vatRate;
    }

    private void validateVatFreePrice(BigDecimal vatFreePrice){

        validateProductAttribute(vatFreePrice == null, ProductErrorMessage.PRODUCT_PRICE_NULL_ERROR);
        validateProductAttribute(vatFreePrice.compareTo(BigDecimal.ZERO) <= 0, ProductErrorMessage.PRODUCT_PRICE_NEGATIVE_ERROR);
    }


    private void validateProductInfoType(ProductType productType) {

        boolean exist = prProductInfoEntityService.existsPrProductInfoByProductType(productType);

        if(!exist){
            throw new GenBusinessException(GenErrorMessage.ITEM_NOT_FOUND);
        }

    }
}
