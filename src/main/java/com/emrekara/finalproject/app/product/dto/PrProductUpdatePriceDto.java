package com.emrekara.finalproject.app.product.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrProductUpdatePriceDto {

    private Long id;
    private BigDecimal vatFreePrice;
}
