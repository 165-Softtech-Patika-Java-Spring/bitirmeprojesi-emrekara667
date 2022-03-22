package com.emrekara.finalproject.app.productInfo.dao;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrProductInfoDao extends JpaRepository<PrProductInfo, Long> {

    boolean existsPrProductInfoByProductType(ProductType productType);

    PrProductInfo findByProductType(ProductType productType);



}
