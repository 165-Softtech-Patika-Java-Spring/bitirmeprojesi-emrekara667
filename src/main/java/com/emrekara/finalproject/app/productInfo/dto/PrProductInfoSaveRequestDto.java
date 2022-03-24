package com.emrekara.finalproject.app.productInfo.dto;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@Builder
public class PrProductInfoSaveRequestDto {

    private ProductType productType;
    private BigDecimal vatRate;
}
