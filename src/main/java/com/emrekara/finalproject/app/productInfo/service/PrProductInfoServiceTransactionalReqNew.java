package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PrProductInfoServiceTransactionalReqNew {

    private final PrProductInfoEntityService prProductInfoEntityService;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveProductInfoEntity(PrProductInfo prProductInfo){
        prProductInfoEntityService.save(prProductInfo);
    }


}
