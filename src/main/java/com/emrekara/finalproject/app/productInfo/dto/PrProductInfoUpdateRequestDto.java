package com.emrekara.finalproject.app.productInfo.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrProductInfoUpdateRequestDto {

    private ProductType productType;
    private BigDecimal vatRate;
}
