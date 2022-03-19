package com.emrekara.finalproject.app.product.service.entityservice;

import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.product.dao.PrProductDao;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.springframework.stereotype.Service;

@Service
public class PrProductEntityService extends BaseEntityService<PrProduct, PrProductDao> {

    public PrProductEntityService(PrProductDao dao) {
        super(dao);
    }
}
