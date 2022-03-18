package com.emrekara.finalproject.app.productInfo.dao;

import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrProductInfoDao extends JpaRepository<PrProductInfo, Long> {
}
