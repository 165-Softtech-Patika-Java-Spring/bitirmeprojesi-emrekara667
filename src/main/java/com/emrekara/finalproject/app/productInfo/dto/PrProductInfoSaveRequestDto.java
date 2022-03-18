package com.emrekara.finalproject.app.productInfo.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class PrProductInfoSaveRequestDto {

    private ProductType productType;
    private BigDecimal vatRate;
}
