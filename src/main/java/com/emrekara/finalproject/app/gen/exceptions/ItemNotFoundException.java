package com.emrekara.finalproject.app.gen.exceptions;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenBusinessException{

    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }

    public ItemNotFoundException(BaseErrorMessage baseErrorMessage, String message) {
        super(baseErrorMessage, message);
    }
}
