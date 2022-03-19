package com.emrekara.finalproject.app.user.service;

import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.gen.exceptions.ItemNotFoundException;
import com.emrekara.finalproject.app.user.converter.UsUserMapper;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserResponseDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.dto.UsUserUpdateRequestDto;
import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.enums.UsrErrorMessage;
import com.emrekara.finalproject.app.user.service.entityservice.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsUserService {

    private final UsUserEntityService usUserEntityService;

    public UsUserDto save(UsUserSaveRequestDto usUserSaveRequestDto) {

        UsUser usUser = UsUserMapper.INSTANCE.convertToUsUser(usUserSaveRequestDto);

        boolean isExistByUserName = usUserEntityService.existsUsUserByUserName(usUser.getUserName());

        if(!isExistByUserName){
            usUser = usUserEntityService.save(usUser);
        }else{
           throw new GenBusinessException(UsrErrorMessage.USERNAME_ALREADY_EXIST_ERROR);
        }
        UsUserDto usUserDto = UsUserMapper.INSTANCE.convertToUsUserDto(usUser);

        return usUserDto;
    }

    public UsUserDto update(UsUserUpdateRequestDto usUserUpdateRequestDto) {

        controlIsUserExist(usUserUpdateRequestDto);

        UsUser usUser = UsUserMapper.INSTANCE.convertToUsUser(usUserUpdateRequestDto);

        usUserEntityService.save(usUser);

        UsUserDto usUserDto = UsUserMapper.INSTANCE.convertToUsUserDto(usUser);

        return usUserDto;
    }

    //Todo: username update should prevent
    private void controlIsUserExist(UsUserUpdateRequestDto usUserUpdateRequestDto) {

        Long id = usUserUpdateRequestDto.getId();

        boolean isExist = usUserEntityService.existsById(id);
        if(!isExist){
            throw new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND_ERROR);
        }

    }

    public void delete(Long id) {
       UsUser usUser = usUserEntityService.getByIdWithControl(id);

       usUserEntityService.delete(usUser);
    }

    public List<UsUserResponseDto> findAll() {

        List<UsUser> usUserList = usUserEntityService.findAll();

        List<UsUserResponseDto> usUserResponseDtoList = UsUserMapper.INSTANCE.convertToUsUserResponseDtoList(usUserList);

        return usUserResponseDtoList;
    }
}
