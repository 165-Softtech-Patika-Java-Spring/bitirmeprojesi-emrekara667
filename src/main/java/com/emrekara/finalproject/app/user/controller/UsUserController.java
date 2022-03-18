package com.emrekara.finalproject.app.user.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import com.emrekara.finalproject.app.user.dto.UsUserUpdateRequestDto;
import com.emrekara.finalproject.app.user.service.UsUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsUserController {

    private final UsUserService usUserService;


    // Todo : add hateoas and  swagger model
    @Operation(tags = "User Controller", description = "Save new User", summary = "Register new user")
    @PostMapping
    public ResponseEntity save(@RequestBody UsUserSaveRequestDto usUserSaveRequestDto){

        UsUserDto usUserDto = usUserService.save(usUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usUserDto));
    }

    @Operation(tags = "User Controller", description = "Update name-surname-password", summary = "Update user info")
    @PutMapping
    public ResponseEntity update(@RequestBody UsUserUpdateRequestDto usUserUpdateRequestDto){
        UsUserDto usUserDto = usUserService.update(usUserUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(usUserDto));
    }
}
