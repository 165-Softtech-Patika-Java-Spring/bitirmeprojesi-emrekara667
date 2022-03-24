package com.emrekara.finalproject.app.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UsUserSaveRequestDto {

    @NotNull(message = "name can not be null")
    @NotEmpty(message = "User name can not be null")
    private String name;
    @NotNull(message = "surname can not be null")
    @NotEmpty(message = "User surname can not be null")
    private String surname;
    @NotNull(message = "password can not be null")
    @NotEmpty(message = "User password can not be null")
    private String password;
    @NotNull(message = "userName can not be null")
    @NotEmpty(message = "User userName can not be null")
    private String userName;
}
