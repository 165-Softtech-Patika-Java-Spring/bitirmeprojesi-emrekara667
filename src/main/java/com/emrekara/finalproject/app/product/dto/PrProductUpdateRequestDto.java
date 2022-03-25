package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PrProductUpdateRequestDto {

    private Long id;
    private String productName;
    private ProductType productType;
    private BigDecimal vatFreePrice;
}
