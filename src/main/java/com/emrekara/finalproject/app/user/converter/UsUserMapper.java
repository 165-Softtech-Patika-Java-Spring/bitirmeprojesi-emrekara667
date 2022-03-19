package com.emrekara.finalproject.app.user.converter;

import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserResponseDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.dto.UsUserUpdateRequestDto;
import com.emrekara.finalproject.app.user.entity.UsUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsUserMapper {

    UsUserMapper INSTANCE = Mappers.getMapper(UsUserMapper.class);

    UsUser convertToUsUser(UsUserSaveRequestDto usUserSaveRequestDto);

    UsUserDto convertToUsUserDto(UsUser usUser);

    UsUser convertToUsUser(UsUserUpdateRequestDto usUserUpdateRequestDto);

    UsUserSaveRequestDto convertToUsUserSaveRequestDto(UsUser usUser);

    List<UsUserResponseDto> convertToUsUserResponseDtoList(List<UsUser> userList);

}
