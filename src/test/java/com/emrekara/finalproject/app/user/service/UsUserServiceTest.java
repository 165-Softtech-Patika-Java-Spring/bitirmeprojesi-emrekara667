package com.emrekara.finalproject.app.user.service;

import com.emrekara.finalproject.app.user.dto.UsUserResponseDto;
import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.service.entityservice.UsUserEntityService;
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
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {

        UsUser usUser = new UsUser();
        ArrayList<UsUser> usUserArrayList = new ArrayList<>();
        usUserArrayList.add(usUser);

        when(usUserEntityService.findAll()).thenReturn(usUserArrayList);

        List<UsUserResponseDto> usUserResponseDtos = usUserService.findAll();

        assertEquals(usUserArrayList.size(), usUserResponseDtos.size());
    }
}