package com.emrekara.finalproject.app.product.converter;

import com.emrekara.finalproject.app.product.dto.*;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrProductMapper {

    PrProductMapper INSTANCE = Mappers.getMapper(PrProductMapper.class);

    PrProductDto convertToPrProductDto(PrProduct prProduct);

    PrProductSaveRequestDto convertToPrProductSaveRequestDto(PrProductUpdateRequestDto prProductUpdateRequestDto);

    List<PrProductDto> convertToProductDtoList(List<PrProduct> prProductList);

    List<PrProductUpdatePriceDto> convertToPrProductUpdatePriceDtoList(List<PrProductDto> productDtoList);

    PrProductDetailsDto convertToPrProductDetailsDto(PrProductDetails prProductDetails);
}
