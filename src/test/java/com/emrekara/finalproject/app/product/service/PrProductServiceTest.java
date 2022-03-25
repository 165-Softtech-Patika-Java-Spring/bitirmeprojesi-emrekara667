package com.emrekara.finalproject.app.product.service;

import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import com.emrekara.finalproject.app.product.service.entityservice.PrProductEntityService;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import org.intellij.lang.annotations.MagicConstant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void save() {

        PrProduct prProduct = new PrProduct();
        ArrayList<PrProduct> prProductArrayList = new ArrayList<>();
        prProductArrayList.add(prProduct);

        when(prProductEntityService.findAll()).thenReturn(prProductArrayList);

        List<PrProductDto> prProductServiceAll = prProductService.findAll();

        assertEquals(prProductArrayList.size(), prProductServiceAll.size());

    }

    @Test
    void delete() {
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