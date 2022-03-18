package com.emrekara.finalproject.app.user.enums;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;

public enum UsrErrorMessage implements BaseErrorMessage {

    USERNAME_ALREADY_EXIST_ERROR("UserName already exist!"),
    USER_NOT_FOUND_ERROR("User entity does not exist");
    ;

    private String message;

    UsrErrorMessage(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
