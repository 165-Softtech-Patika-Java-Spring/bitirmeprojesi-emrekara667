package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrProductInfoServiceTransactionalReqNewTest {

    @InjectMocks
    private PrProductInfoServiceTransactionalReqNew productInfoServiceTransactionalReqNew;

    @Mock
    private PrProductInfoEntityService prProductInfoEntityService;


    @Test
    void saveProductInfoEntity() {

        PrProductInfo prProductInfo = mock(PrProductInfo.class);

        productInfoServiceTransactionalReqNew.saveProductInfoEntity(prProductInfo);

        verify(prProductInfoEntityService).save(prProductInfo);

    }
}