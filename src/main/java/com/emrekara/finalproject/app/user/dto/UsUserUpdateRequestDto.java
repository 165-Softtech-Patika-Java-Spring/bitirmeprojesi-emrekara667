package com.emrekara.finalproject.app.user.dto;

import lombok.Data;

@Data
public class UsUserUpdateRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String password;
    private String userName;
}
