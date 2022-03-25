package com.emrekara.finalproject.app.product.service;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.dto.PrProductUpdatePriceDto;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import com.emrekara.finalproject.app.product.service.entityservice.PrProductEntityService;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import com.emrekara.finalproject.app.user.entity.UsUser;
import org.intellij.lang.annotations.MagicConstant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrProductServiceTest {

    @InjectMocks
    private PrProductService prProductService;

    @Mock
    private PrProductEntityService prProductEntityService;

    @Mock
    private PrProductInfoEntityService prProductInfoEntityService;


    @Test
    void shouldSave() {

        PrProduct prProduct = new PrProduct();
        ArrayList<PrProduct> prProductArrayList = new ArrayList<>();
        prProductArrayList.add(prProduct);

        when(prProductEntityService.findAll()).thenReturn(prProductArrayList);

        List<PrProductDto> prProductServiceAll = prProductService.findAll();

        assertEquals(prProductArrayList.size(), prProductServiceAll.size());

    }

    @Test
    void delete() {

        PrProduct prProduct = mock(PrProduct.class);


        when(prProductEntityService.getByIdWithControl(anyLong())).thenReturn(prProduct);

        prProductService.delete(anyLong());
        verify(prProductEntityService).getByIdWithControl(anyLong());
        verify(prProductEntityService).delete(any());

    }

    @Test
    void updatePrice() {


    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByProduct() {
    }

    @Test
    void findAllByFinalPriceBetween() {
    }

    @Test
    void findProductDetails() {
    }
}