package com.emrekara.finalproject.app.product.dao;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.dto.PrProductDetails;
import com.emrekara.finalproject.app.product.dto.PrProductDetailsDto;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrProductDao extends JpaRepository<PrProduct, Long> {

    List<PrProduct> findAllByProductType(ProductType productType);

    List<PrProduct> findAllByFinalPriceBetween(BigDecimal minimum, BigDecimal maximum);

    @Query(" select min(prProduct.finalPrice) from PrProduct prProduct where prProduct.productType = :productType ")
    BigDecimal findMinPrice(ProductType productType);

    @Query(" select max(prProduct.finalPrice) from PrProduct prProduct where prProduct.productType = :productType ")
    BigDecimal findMaxPrice(ProductType productType);

    @Query(" select avg(prProduct.finalPrice) from PrProduct prProduct where prProduct.productType = :productType ")
    BigDecimal findAvgPrice(ProductType productType);

    @Query(" select count(prProduct) from PrProduct prProduct where prProduct.productType = :productType ")
    BigDecimal findCount(ProductType productType);


    @Query(" select " +
            " new com.emrekara.finalproject.app.product.dto.PrProductDetails( " +
            " min(prProduct.finalPrice), " +
            " max(prProduct.finalPrice), " +
            " avg(prProduct.finalPrice) , " +
            " count(prProduct) " +
            " ) " +
            " from PrProduct prProduct where prProduct.productType = :productType ")
    PrProductDetails getProductDetails(ProductType productType);


}
