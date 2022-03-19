package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PrProductSaveRequestDto {

    @NotNull
    private String productName;
    @NotNull
    private ProductType productType;
    @NotNull
    private BigDecimal vatFreePrice;
}
