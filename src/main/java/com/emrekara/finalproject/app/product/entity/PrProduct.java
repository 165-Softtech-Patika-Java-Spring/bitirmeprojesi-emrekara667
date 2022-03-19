package com.emrekara.finalproject.app.product.entity;

import com.emrekara.finalproject.app.gen.entity.BaseEntity;
import com.emrekara.finalproject.app.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PR_PRODUCT")
@Getter
@Setter
public class PrProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PrProduct", sequenceName = "PR_PRODUCT_ID_SEQ")
    @GeneratedValue(generator = "PrProduct")
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 100, nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", length = 30, nullable = false)
    private ProductType productType;

    @Column(name = "VAT_FREE_PRICE", precision = 19, scale = 2, nullable = false)
    private BigDecimal vatFreePrice;

    @Column(name = "FINAL_PRICE", precision = 19, scale = 2)
    private BigDecimal finalPrice;

    @Column(name = "ID_PR_PRODUCT_INFO")
    private Long productInfoId;

    @Column(name = "VAT_PRICE", precision = 19, scale = 2)
    private BigDecimal vatPrice;
}
