package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PrProductSaveRequestDto {


    @NotNull(message = "Cannot be null")
    private String productName;

    @NotNull(message = "Cannot be null")
    private ProductType productType;

    @NotNull(message = "Cannot be null")
    private BigDecimal vatFreePrice;
}
