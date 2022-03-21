package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class PrProductDetails {

    private final BigDecimal minimum;
    private final BigDecimal maximum;
    private final Double average;
    private final Long count;
    private  ProductType productType;
    private  BigDecimal vatRate;
}
