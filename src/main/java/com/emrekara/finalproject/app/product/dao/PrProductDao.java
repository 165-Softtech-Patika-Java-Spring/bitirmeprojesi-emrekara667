package com.emrekara.finalproject.app.product.dao;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrProductDao extends JpaRepository<PrProduct, Long> {

    List<PrProduct> findAllByProductType(ProductType productType);

    List<PrProduct> findAllByFinalPriceBetween(BigDecimal minimum, BigDecimal maximum);
}
