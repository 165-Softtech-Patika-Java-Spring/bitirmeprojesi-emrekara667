package com.emrekara.finalproject.app.productInfo.enums;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;

public enum PrProductErrorMessage implements BaseErrorMessage {

    NEGATIVE_VAT_RATE_ERROR("Negative vat rate error"),
    PRODUCT_TYPE_ALREADY_EXIST_ERROR("Product type already exist!"),
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
