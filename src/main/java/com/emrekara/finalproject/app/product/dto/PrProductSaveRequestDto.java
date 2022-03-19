package com.emrekara.finalproject.app.product.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PrProductSaveRequestDto {


    private String productName;

    private ProductType productType;

    private BigDecimal vatFreePrice;
}
