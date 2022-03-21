package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.product.converter.PrProductMapper;
import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.dto.PrProductUpdatePriceDto;
import com.emrekara.finalproject.app.product.service.PrProductService;
import com.emrekara.finalproject.app.productInfo.converter.PrProductInfoMapper;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoUpdateRequestDto;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.enums.PrProductInfoErrorMessage;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrProductInfoService {

    private final PrProductInfoEntityService prProductInfoEntityService;
    private final PrProductService prProductService;

    public PrProductInfoDto save(PrProductInfoSaveRequestDto prProductInfoSaveRequestDto) {

        PrProductInfo prProductInfo = PrProductInfoMapper.INSTANCE.convertToPrProductInfo(prProductInfoSaveRequestDto);

        BigDecimal vatRate = prProductInfo.getVatRate();
        validateVatRate(vatRate);

        validateProductTypeisExist(prProductInfo);

        prProductInfo = prProductInfoEntityService.save(prProductInfo);

        PrProductInfoDto prProductInfoDto = PrProductInfoMapper.INSTANCE.convertToPrProductInfoDto(prProductInfo);

        return prProductInfoDto;
    }

    private void validateProductTypeisExist(PrProductInfo prProductInfo) {
        boolean isExist = prProductInfoEntityService.existsPrProductInfoByProductType(prProductInfo.getProductType());

        validateProductInfoAttribute(isExist,PrProductInfoErrorMessage.PRODUCT_TYPE_ALREADY_EXIST_ERROR);
    }

    private void validateProductInfoAttribute(boolean productInfoAttribute, PrProductInfoErrorMessage errorMessage) {
        if(productInfoAttribute){
            throw new GenBusinessException(errorMessage);
        }
    }


    private void validateVatRate(BigDecimal vatRate) {
        if(vatRate.compareTo(BigDecimal.ZERO) < 0){
            throw new GenBusinessException(PrProductInfoErrorMessage.NEGATIVE_VAT_RATE_ERROR);
        }
    }

    public List<PrProductInfoDto> findAll() {
        List<PrProductInfo> prProductInfoList = prProductInfoEntityService.findAll();

        List<PrProductInfoDto> prProductInfoDtoList = PrProductInfoMapper.INSTANCE.
                convertToPrProductInfoDtoList(prProductInfoList);

        return prProductInfoDtoList;
    }

    public PrProductInfoDto updateVatRate(PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto) {

        setVatRateTransactional(prProductInfoUpdateRequestDto);
        ProductType productType = prProductInfoUpdateRequestDto.getProductType();

        updateAllProductPriceByProductType(productType);

        PrProductInfo prProductInfo = prProductInfoEntityService.findByProductType(productType);

        PrProductInfoDto prProductInfoDto = PrProductInfoMapper.INSTANCE.convertToPrProductInfoDto(prProductInfo);

        return prProductInfoDto;
    }

    private void updateAllProductPriceByProductType(ProductType productType) {

        List<PrProductDto> productDtoList = prProductService.findAllByProduct(productType);

        List<PrProductUpdatePriceDto> prProductUpdatePriceDtoList = PrProductMapper.INSTANCE.
                convertToPrProductUpdatePriceDtoList(productDtoList);

        for(PrProductUpdatePriceDto tempDto: prProductUpdatePriceDtoList){
            prProductService.updatePrice(tempDto);
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void setVatRateTransactional(PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto) {
        BigDecimal vatRate = prProductInfoUpdateRequestDto.getVatRate();
        ProductType productType = prProductInfoUpdateRequestDto.getProductType();

        validateProductTypeisNotExist(productType);

        PrProductInfo prProductInfo = prProductInfoEntityService.findByProductType(productType);

        prProductInfo.setVatRate(vatRate);

        prProductInfoEntityService.save(prProductInfo);
    }

    private void validateProductTypeisNotExist(ProductType productType) {
        boolean isExist = prProductInfoEntityService.existsPrProductInfoByProductType(productType);

        validateProductInfoAttribute(!isExist,PrProductInfoErrorMessage.PRODUCT_TYPE_NOT_FOUND_ERROR);
    }
}
