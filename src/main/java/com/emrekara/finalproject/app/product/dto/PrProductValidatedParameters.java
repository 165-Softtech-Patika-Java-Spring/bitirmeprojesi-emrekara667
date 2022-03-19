package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrProductValidatedParameters {

    String productName;
    ProductType productType;
    BigDecimal vatFreePrice;
}
