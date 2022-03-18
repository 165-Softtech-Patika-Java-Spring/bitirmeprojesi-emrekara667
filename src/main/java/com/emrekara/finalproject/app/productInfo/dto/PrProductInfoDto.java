package com.emrekara.finalproject.app.productInfo.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class PrProductInfoDto {

    private Long id;
    private ProductType productType;
    private BigDecimal vatRate;
}
