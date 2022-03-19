package com.emrekara.finalproject.app.product.dao;

import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrProductDao extends JpaRepository<PrProduct, Long> {
}
