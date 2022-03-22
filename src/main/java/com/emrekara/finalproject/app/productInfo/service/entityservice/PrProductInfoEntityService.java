package com.emrekara.finalproject.app.productInfo.service.entityservice;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.productInfo.dao.PrProductInfoDao;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.enums.PrProductInfoErrorMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PrProductInfoEntityService extends BaseEntityService<PrProductInfo, PrProductInfoDao> {

    public PrProductInfoEntityService(PrProductInfoDao dao) {
        super(dao);
    }

    public boolean existsPrProductInfoByProductType(ProductType productType){
        return getDao().existsPrProductInfoByProductType(productType);
    }

    public PrProductInfo findByProductType(ProductType productType){
        return getDao().findByProductType(productType);
    }


    public void validateProductTypeExist(ProductType productType){
        boolean isExist = getDao().existsPrProductInfoByProductType(productType);

        if(!isExist){
            throw new GenBusinessException(PrProductInfoErrorMessage.PRODUCT_TYPE_NOT_FOUND_ERROR);
        }
    }




}
