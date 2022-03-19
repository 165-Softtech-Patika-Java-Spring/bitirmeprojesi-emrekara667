package com.emrekara.finalproject.app.product.service;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.converter.PrProductMapper;
import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.dto.PrProductSaveRequestDto;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import com.emrekara.finalproject.app.product.service.entityservice.PrProductEntityService;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PrProductService {

    private final PrProductEntityService prProductEntityService;
    private final PrProductInfoEntityService prProductInfoEntityService;

    public PrProductDto save(PrProductSaveRequestDto prProductSaveRequestDto) {

        String productName = prProductSaveRequestDto.getProductName();
        ProductType productType = prProductSaveRequestDto.getProductType();
        BigDecimal vatFreePrice = prProductSaveRequestDto.getVatFreePrice();

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

        prProduct = prProductEntityService.save(prProduct);

        PrProductDto prProductDto = PrProductMapper.INSTANCE.convertToPrProductDto(prProduct);

        return prProductDto;
    }


    private BigDecimal calculateFinalPrice(BigDecimal vatFreePrice, BigDecimal vatPrice) {
        return vatFreePrice.add(vatPrice);
    }

    private BigDecimal calculateVatPrice(BigDecimal vatFreePrice, BigDecimal vatRate) {

        BigDecimal dividedVatRate = vatRate.divide(BigDecimal.valueOf(100));

        return vatFreePrice.multiply(dividedVatRate);
    }
}
