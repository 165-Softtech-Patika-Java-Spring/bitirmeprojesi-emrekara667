package com.emrekara.finalproject.app.productInfo.converter;

import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoUpdateRequestDto;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrProductInfoMapper {

    PrProductInfoMapper INSTANCE = Mappers.getMapper(PrProductInfoMapper.class);

    PrProductInfo convertToPrProductInfo(PrProductInfoSaveRequestDto prProductInfoSaveRequestDto);

    PrProductInfoDto convertToPrProductInfoDto(PrProductInfo prProductInfo);

    List<PrProductInfoDto> convertToPrProductInfoDtoList(List<PrProductInfo> prProductInfo);

    PrProductInfo convertToPrProductInfo(PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto);
}
