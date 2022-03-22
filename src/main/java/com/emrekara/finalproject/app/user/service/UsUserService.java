package com.emrekara.finalproject.app.user.service;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;
import com.emrekara.finalproject.app.gen.exceptions.BadRequestExceptions;
import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.user.converter.UsUserMapper;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserResponseDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.dto.UsUserUpdateRequestDto;
import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.enums.UsrErrorMessage;
import com.emrekara.finalproject.app.user.service.entityservice.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsUserService {

    private final UsUserEntityService usUserEntityService;
    private final PasswordEncoder passwordEncoder;

    public UsUserDto save(UsUserSaveRequestDto usUserSaveRequestDto) {

        UsUser usUser = UsUserMapper.INSTANCE.convertToUsUser(usUserSaveRequestDto);

        validateUserName(usUser.getUserName());

        String password = passwordEncoder.encode(usUser.getPassword());
        usUser.setPassword(password);

        usUser = usUserEntityService.save(usUser);

        UsUserDto usUserDto = UsUserMapper.INSTANCE.convertToUsUserDto(usUser);

        return usUserDto;
    }

    public UsUserDto update(UsUserUpdateRequestDto usUserUpdateRequestDto) {

        updateParametersControl(usUserUpdateRequestDto);

        UsUser usUser = UsUserMapper.INSTANCE.convertToUsUser(usUserUpdateRequestDto);

        String password = passwordEncoder.encode(usUser.getPassword());
        usUser.setPassword(password);


        usUserEntityService.save(usUser);

        UsUserDto usUserDto = UsUserMapper.INSTANCE.convertToUsUserDto(usUser);

        return usUserDto;
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


    private void validateUserName(String userName) {
        boolean isExistByUserName = usUserEntityService.existsUsUserByUserName(userName);

        validateAttribute(isExistByUserName , UsrErrorMessage.USERNAME_ALREADY_EXIST_ERROR);
    }

    private void updateParametersControl(UsUserUpdateRequestDto usUserUpdateRequestDto) {

        UsUser usUser = getUsUserWithControl(usUserUpdateRequestDto);

        String dtoUserName = usUserUpdateRequestDto.getUserName();

        if(!usUser.getUserName().equals(dtoUserName)){
            if(usUserEntityService.existsUsUserByUserName(dtoUserName)){
                throw new BadRequestExceptions(UsrErrorMessage.USERNAME_ALREADY_EXIST_ERROR);
            }
        }
    }

    private UsUser getUsUserWithControl(@NotNull UsUserUpdateRequestDto usUserUpdateRequestDto) {
        Long id = usUserUpdateRequestDto.getId();

       return usUserEntityService.getByIdWithControl(id);
    }

    private void validateAttribute(boolean attribute, BaseErrorMessage baseErrorMessage) {
        if(attribute){
            throw new BadRequestExceptions(baseErrorMessage);
        }
    }
}
