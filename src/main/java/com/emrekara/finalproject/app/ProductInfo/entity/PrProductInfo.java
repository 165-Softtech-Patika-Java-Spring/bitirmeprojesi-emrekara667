package com.emrekara.finalproject.app.ProductInfo.entity;


import com.emrekara.finalproject.app.gen.entity.BaseEntity;
import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PR_PRODUCT_INFO")
@Getter
@Setter
public class PrProductInfo extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PrProductInfo" , sequenceName = "PR_PRODUCT _ID_SEQ")
    @GeneratedValue(generator = "PrProductInfo")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", length = 30)
    private ProductType productType;

    @Column(name = "VAT_RATE", precision = 19, scale = 2)
    private BigDecimal  vatRate;

}
