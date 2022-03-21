package com.emrekara.finalproject.app.gen.exceptions;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptions extends GenBusinessException{

    public BadRequestExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

    public BadRequestExceptions(BaseErrorMessage baseErrorMessage, String... parameters) {
        super(baseErrorMessage, parameters);
    }
}
