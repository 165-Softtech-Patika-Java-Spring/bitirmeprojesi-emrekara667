package com.emrekara.finalproject.app.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsUserUpdateRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String password;
    private String userName;
}
