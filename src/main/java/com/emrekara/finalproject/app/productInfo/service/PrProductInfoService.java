package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.productInfo.converter.PrProductInfoMapper;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.enums.PrProductErrorMessage;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PrProductInfoService {

    private final PrProductInfoEntityService prProductInfoEntityService;

    public PrProductInfoDto save(PrProductInfoSaveRequestDto prProductInfoSaveRequestDto) {

        PrProductInfo prProductInfo = PrProductInfoMapper.INSTANCE.convertToPrProductInfo(prProductInfoSaveRequestDto);

        BigDecimal vatRate = prProductInfo.getVatRate();
        validateVatRate(vatRate);

        prProductInfo = prProductInfoEntityService.save(prProductInfo);

        PrProductInfoDto prProductInfoDto = PrProductInfoMapper.INSTANCE.convertToPrProductInfoDto(prProductInfo);

        return prProductInfoDto;
    }

    private void validateVatRate(BigDecimal vatRate) {
        if(vatRate.compareTo(BigDecimal.ZERO) < 0){
            throw new GenBusinessException(PrProductErrorMessage.NEGATIVE_VAT_RATE_ERROR);
        }
    }
}
