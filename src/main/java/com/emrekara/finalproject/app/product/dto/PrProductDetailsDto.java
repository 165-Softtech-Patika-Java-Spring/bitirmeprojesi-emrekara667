package com.emrekara.finalproject.app.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrProductDetailsDto {

    private BigDecimal minimum;
    private BigDecimal maximum;
    private Double average;
    private Long count;
}
