package com.emrekara.finalproject.app.user.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.service.UsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsUserController {

    private final UsUserService usUserService;

    @PostMapping
    public ResponseEntity save(@RequestBody UsUserSaveRequestDto usUserSaveRequestDto){

        UsUserDto usUserDto = usUserService.save(usUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usUserDto));
    }
}
