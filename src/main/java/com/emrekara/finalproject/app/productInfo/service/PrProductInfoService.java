package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.product.enums.ProductErrorMessage;
import com.emrekara.finalproject.app.productInfo.converter.PrProductInfoMapper;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.enums.PrProductInfoErrorMessage;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrProductInfoService {

    private final PrProductInfoEntityService prProductInfoEntityService;

    public PrProductInfoDto save(PrProductInfoSaveRequestDto prProductInfoSaveRequestDto) {

        PrProductInfo prProductInfo = PrProductInfoMapper.INSTANCE.convertToPrProductInfo(prProductInfoSaveRequestDto);

        BigDecimal vatRate = prProductInfo.getVatRate();
        validateVatRate(vatRate);

        boolean isExist = prProductInfoEntityService.existsPrProductInfoByProductType(prProductInfo.getProductType());

        validateProductInfoAttribute(isExist,PrProductInfoErrorMessage.PRODUCT_TYPE_ALREADY_EXIST_ERROR);

        prProductInfo = prProductInfoEntityService.save(prProductInfo);

        PrProductInfoDto prProductInfoDto = PrProductInfoMapper.INSTANCE.convertToPrProductInfoDto(prProductInfo);

        return prProductInfoDto;
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
}
