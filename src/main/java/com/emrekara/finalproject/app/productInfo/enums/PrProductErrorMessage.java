package com.emrekara.finalproject.app.productInfo.enums;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;

public enum PrProductErrorMessage implements BaseErrorMessage {

    NEGATIVE_VAT_RATE_ERROR("Negative vat rate error"),
    ;

    private String message;

    PrProductErrorMessage(String message) {
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
