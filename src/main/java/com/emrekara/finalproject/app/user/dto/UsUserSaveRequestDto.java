package com.emrekara.finalproject.app.user.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class UsUserSaveRequestDto {

    @NotNull(message = "name can not be null")
    private String name;
    private String surname;
    private String password;
    private String userName;
}
