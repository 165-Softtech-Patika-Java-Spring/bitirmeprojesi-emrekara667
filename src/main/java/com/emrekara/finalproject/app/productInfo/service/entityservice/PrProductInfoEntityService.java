package com.emrekara.finalproject.app.productInfo.service.entityservice;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.productInfo.dao.PrProductInfoDao;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import org.springframework.stereotype.Service;

@Service
public class PrProductInfoEntityService extends BaseEntityService<PrProductInfo, PrProductInfoDao> {

    public PrProductInfoEntityService(PrProductInfoDao dao) {
        super(dao);
    }

    public boolean existsPrProductInfoByProductType(ProductType productType){
        return getDao().existsPrProductInfoByProductType(productType);
    }
}
