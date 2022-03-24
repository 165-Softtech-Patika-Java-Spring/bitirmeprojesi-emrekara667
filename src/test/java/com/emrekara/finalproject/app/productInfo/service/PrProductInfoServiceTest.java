package com.emrekara.finalproject.app.productInfo.service;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.gen.exceptions.BadRequestExceptions;
import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.dto.PrProductUpdatePriceDto;
import com.emrekara.finalproject.app.product.service.PrProductService;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoUpdateRequestDto;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import com.emrekara.finalproject.app.productInfo.enums.PrProductInfoErrorMessage;
import com.emrekara.finalproject.app.productInfo.service.entityservice.PrProductInfoEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PrProductInfoServiceTest {

    @Spy
    @InjectMocks
    private PrProductInfoService prProductInfoService;

    @Mock
    private PrProductInfoEntityService prProductInfoEntityService;

    @Mock
    private PrProductService prProductService;

    @Mock
    private PrProductInfoServiceTransactionalReqNew prProductInfoServiceTransactionalReqNew;

    @Test
    void shouldFindAll() {

        PrProductInfo prProductInfo = new PrProductInfo();
        List<PrProductInfo> prProductInfoList = new ArrayList<>();
        prProductInfoList.add(prProductInfo);

        when(prProductInfoEntityService.findAll()).thenReturn(prProductInfoList);

        List<PrProductInfoDto> prProductInfoDtos = prProductInfoService.findAll();

        assertEquals(prProductInfoList.size(), prProductInfoDtos.size());
    }

    @Test
    void shouldSave() {

        PrProductInfoSaveRequestDto prProductInfoSaveRequestDto = mock(PrProductInfoSaveRequestDto.class);
        PrProductInfo prProductInfo = mock(PrProductInfo.class);
        when(prProductInfo.getId()).thenReturn(1L);

        when(prProductInfoSaveRequestDto.getVatRate()).thenReturn(BigDecimal.ONE);
        when(prProductInfo.getProductType()).thenReturn(ProductType.FOOD);

        when(prProductInfoEntityService.existsPrProductInfoByProductType(any())).thenReturn(false);

        when(prProductInfoEntityService.save(any())).thenReturn(prProductInfo);

        PrProductInfoDto prProductInfoDto = prProductInfoService.save(prProductInfoSaveRequestDto);

        assertEquals(1L, prProductInfoDto.getId());
    }

    @Test
    void shouldNotSaveWhenVatRateIsNegative() {

        PrProductInfoSaveRequestDto prProductInfoSaveRequestDto = mock(PrProductInfoSaveRequestDto.class);

        when(prProductInfoSaveRequestDto.getVatRate()).thenReturn(new BigDecimal(-1));

        BadRequestExceptions badRequestExceptions = assertThrows(BadRequestExceptions.class,
                () -> prProductInfoService.save(prProductInfoSaveRequestDto));

        assertEquals(PrProductInfoErrorMessage.NEGATIVE_VAT_RATE_ERROR.getMessage(), badRequestExceptions.getMessage());

    }

    @Test
    void shouldNotSaveWhenProductTypeShouldNotExist() {

        PrProductInfoSaveRequestDto prProductInfoSaveRequestDto = mock(PrProductInfoSaveRequestDto.class);
        PrProductInfo prProductInfo = mock(PrProductInfo.class);

        when(prProductInfoSaveRequestDto.getVatRate()).thenReturn(BigDecimal.ONE);

        when(prProductInfoEntityService.existsPrProductInfoByProductType(any())).thenReturn(true);

        BadRequestExceptions badRequestExceptions = assertThrows(BadRequestExceptions.class,
                () -> prProductInfoService.save(prProductInfoSaveRequestDto));

        assertEquals(PrProductInfoErrorMessage.PRODUCT_TYPE_ALREADY_EXIST_ERROR.getMessage(),
                badRequestExceptions.getMessage());

    }

    @Test
    void updateVatRate() {

        PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto = mock(PrProductInfoUpdateRequestDto.class);

        when(prProductInfoUpdateRequestDto.getVatRate()).thenReturn(BigDecimal.ONE);

        doNothing().when(prProductInfoService).setVatRateTransactional(prProductInfoUpdateRequestDto);

        when(prProductInfoUpdateRequestDto.getProductType()).thenReturn(ProductType.FOOD);

        PrProductDto prProductDto = mock(PrProductDto.class);
        List<PrProductDto> prProductDtoList = new ArrayList<>();
        when(prProductService.findAllByProduct(any())).thenReturn(prProductDtoList);

        PrProductUpdatePriceDto prProductUpdatePriceDto = mock(PrProductUpdatePriceDto.class);

        PrProductInfo prProductInfo = mock(PrProductInfo.class);
        //prProductInfo.setProductType(ProductType.FOOD);

        when(prProductInfoEntityService.findByProductType(any())).thenReturn(prProductInfo);
        when(prProductInfo.getProductType()).thenReturn(ProductType.FOOD);


        PrProductInfoDto prProductInfoDto = prProductInfoService.updateVatRate(prProductInfoUpdateRequestDto);

        assertEquals(ProductType.FOOD, prProductInfoDto.getProductType());
    }


    @Test
    void shouldSetVatRateTransactional() {

        PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto = mock(PrProductInfoUpdateRequestDto.class);

        when(prProductInfoEntityService.existsPrProductInfoByProductType(any())).thenReturn(true);

        PrProductInfo prProductInfo = mock(PrProductInfo.class);

        when(prProductInfoEntityService.findByProductType(any())).thenReturn(prProductInfo);

        prProductInfoService.setVatRateTransactional(prProductInfoUpdateRequestDto);

        verify(prProductInfoServiceTransactionalReqNew).saveProductInfoEntity(prProductInfo);
    }
}