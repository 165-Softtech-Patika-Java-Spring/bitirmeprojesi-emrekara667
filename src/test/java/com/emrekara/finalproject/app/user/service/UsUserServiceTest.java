package com.emrekara.finalproject.app.user.service;

import com.emrekara.finalproject.app.gen.exceptions.BadRequestExceptions;
import com.emrekara.finalproject.app.gen.exceptions.ItemNotFoundException;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserResponseDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.dto.UsUserUpdateRequestDto;
import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.enums.UsrErrorMessage;
import com.emrekara.finalproject.app.user.service.entityservice.UsUserEntityService;
import io.jsonwebtoken.JwsHeader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsUserServiceTest {

    @InjectMocks
    UsUserService usUserService;

    @Mock
    UsUserEntityService usUserEntityService;

    @Mock
    PasswordEncoder passwordEncoder;



    @Test
    void shouldSave() {

        UsUserSaveRequestDto usUserSaveRequestDto = mock(UsUserSaveRequestDto.class);
        when(usUserSaveRequestDto.getPassword()).thenReturn("123");


        UsUser usUser = mock(UsUser.class);
        when(usUser.getId()).thenReturn(1L);

        when(passwordEncoder.encode(anyString())).thenReturn("12345");
        when(usUserEntityService.save(any())).thenReturn(usUser);


        UsUserDto result = usUserService.save(usUserSaveRequestDto);

        assertEquals(1L, result.getId());
    }

    /*
    @Test
    void shouldNotSaveWhenUserExist() {

        UsUserSaveRequestDto usUserSaveRequestDto = mock(UsUserSaveRequestDto.class);
        UsUser usUser = mock(UsUser.class);

        when(usUser.getUserName()).thenReturn(anyString());

        when(usUserEntityService.existsUsUserByUserName(anyString())).thenReturn(true);

        assertThrows(BadRequestExceptions.class, ()-> usUserService.save(usUserSaveRequestDto));
    }

*/

    @Test
    void shouldUpdateWhenUserNameSame() {

        UsUserUpdateRequestDto usUserUpdateRequestDto = mock(UsUserUpdateRequestDto.class);
        UsUser usUser = mock(UsUser.class);
        Long id = 18L;
        when(usUser.getId()).thenReturn(id);
        when(usUserUpdateRequestDto.getPassword()).thenReturn("123");

        when(usUserEntityService.getByIdWithControl(anyLong())).thenReturn(usUser);
        when(usUserUpdateRequestDto.getUserName()).thenReturn("emre");
        when(usUser.getUserName()).thenReturn("emre");

        when(passwordEncoder.encode(anyString())).thenReturn("bahadir_1234_123");
        when(usUserEntityService.save(any())).thenReturn(usUser);

        UsUserDto usUserDto = usUserService.update(usUserUpdateRequestDto);

        assertEquals(id, usUserDto.getId());

    }

    @Test
    void shouldUpdateWhenUserNameDifferent() {

        UsUserUpdateRequestDto usUserUpdateRequestDto = mock(UsUserUpdateRequestDto.class);
        UsUser usUser = mock(UsUser.class);
        Long id = 18L;
        when(usUser.getId()).thenReturn(id);
        when(usUserUpdateRequestDto.getPassword()).thenReturn("123");

        when(usUserEntityService.getByIdWithControl(anyLong())).thenReturn(usUser);
        when(usUserUpdateRequestDto.getUserName()).thenReturn("emre");
        when(usUser.getUserName()).thenReturn("hakan");
        when(usUserEntityService.existsUsUserByUserName(anyString())).thenReturn(false);

        when(passwordEncoder.encode(anyString())).thenReturn("bahadir_1234_123");
        when(usUserEntityService.save(any())).thenReturn(usUser);

        UsUserDto usUserDto = usUserService.update(usUserUpdateRequestDto);

        assertEquals(id, usUserDto.getId());

    }

    @Test
    void shouldNotUpdateWhenUserNameExist() {

        UsUserUpdateRequestDto usUserUpdateRequestDto = mock(UsUserUpdateRequestDto.class);
        UsUser usUser = mock(UsUser.class);

        when(usUserEntityService.getByIdWithControl(anyLong())).thenReturn(usUser);
        when(usUser.getUserName()).thenReturn("hakan");
        when(usUserUpdateRequestDto.getUserName()).thenReturn("emre");

        when(usUserEntityService.existsUsUserByUserName(anyString())).thenReturn(true);

        BadRequestExceptions badRequestExceptions = assertThrows(BadRequestExceptions.class,
                () -> usUserService.update(usUserUpdateRequestDto));
    }


    @Test
    void shouldDelete() {

        UsUser usUser = mock(UsUser.class);

       when(usUserEntityService.getByIdWithControl(anyLong())).thenReturn(usUser);

       usUserService.delete(anyLong());

       verify(usUserEntityService).getByIdWithControl(anyLong());
       verify(usUserEntityService).delete(any());

    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {
        when(usUserEntityService.getByIdWithControl(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, ()->usUserService.delete(anyLong()));

        verify(usUserEntityService).getByIdWithControl(anyLong());

    }

    @Test
    void shouldFindAll() {

        UsUser usUser = new UsUser();
        ArrayList<UsUser> usUserArrayList = new ArrayList<>();
        usUserArrayList.add(usUser);

        when(usUserEntityService.findAll()).thenReturn(usUserArrayList);

        List<UsUserResponseDto> usUserResponseDtos = usUserService.findAll();

        assertEquals(usUserArrayList.size(), usUserResponseDtos.size());
    }

    @Test
    void shouldFindAllWhenCustomerListIsEmpty() {

        List<UsUser> usUserList = new ArrayList<>();
        List<UsUserDto> usUserDtos = new ArrayList<>();

        when(usUserEntityService.findAll()).thenReturn(usUserList);

        List<UsUserResponseDto> usUserResponseDtos = usUserService.findAll();

        assertEquals(usUserDtos.size(), usUserResponseDtos.size());
    }


    @Test
    void shouldFindAllWhenCustomerListIsNull() {

        when(usUserEntityService.findAll()).thenReturn(null);

        List<UsUserResponseDto> result = usUserService.findAll();

        assertEquals(null, result);

    }
}