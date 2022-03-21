package com.emrekara.finalproject.app.product.service.entityservice;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.product.dao.PrProductDao;
import com.emrekara.finalproject.app.product.dto.PrProductDetails;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrProductEntityService extends BaseEntityService<PrProduct, PrProductDao> {

    public PrProductEntityService(PrProductDao dao) {
        super(dao);
    }

    public List<PrProduct> findAllByProductType(ProductType productType){
       return getDao().findAllByProductType(productType);
    }

    public List<PrProduct> findAllByFinalPriceBetween(BigDecimal minimum, BigDecimal maximum){
        return getDao().findAllByFinalPriceBetween(minimum, maximum);
    }

    public BigDecimal findMinPrice(ProductType productType){
        return getDao().findMinPrice(productType);
    }

    public BigDecimal findMaxPrice(ProductType productType){
        return getDao().findMaxPrice(productType);
    }

    public BigDecimal findAvgPrice(ProductType productType){
        return getDao().findAvgPrice(productType);
    }

    public BigDecimal findCount(ProductType productType){
        return getDao().findCount(productType);
    }

    public PrProductDetails getProductDetails(ProductType productType){
        return getDao().getProductDetails(productType);
    }
}
