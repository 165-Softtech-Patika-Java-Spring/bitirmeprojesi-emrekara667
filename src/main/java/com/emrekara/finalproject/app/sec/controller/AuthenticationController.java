package com.emrekara.finalproject.app.sec.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.sec.dto.SecLoginRequestDto;
import com.emrekara.finalproject.app.sec.service.AuthenticationService;
import com.emrekara.finalproject.app.user.dto.UsUserDto;
import com.emrekara.finalproject.app.user.dto.UsUserSaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Security Controller",
            description = "Login user",
            summary = "Login user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SecLoginRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "User login",
                                                    summary = "User login Example",
                                                    description = "Complete request with all available fields for login",
                                                    value = "{\n" +
                                                            "  \"userName\": \"emre_kara\",\n" +
                                                            "  \"password\": \"12345\"\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )

    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Security Controller",
            description = "Sign in new user",
            summary = "Sign in new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsUserSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "New User",
                                                    summary = "New User Example",
                                                    description = "Complete request with all available fields for new user",
                                                    value = "{\n" +
                                                            "  \"name\": \"emre\",\n" +
                                                            "  \"surname\": \"kara\",\n" +
                                                            "  \"password\": \"12345\",\n" +
                                                            "  \"userName\": \"emre_kara\"\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsUserSaveRequestDto usUserSaveRequestDto){

        UsUserDto usUserDto = authenticationService.register(usUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usUserDto));
    }


}
