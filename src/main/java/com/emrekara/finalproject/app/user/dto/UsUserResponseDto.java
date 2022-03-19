package com.emrekara.finalproject.app.user.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UsUserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String password;
    private String userName;
}
