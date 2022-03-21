package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrProductDetailsDto {

    private BigDecimal minimum;
    private BigDecimal maximum;
    private Double average;
    private Long count;
    private ProductType productType;
    private BigDecimal vatRate;
}
