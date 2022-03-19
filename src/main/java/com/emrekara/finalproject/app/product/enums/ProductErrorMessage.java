package com.emrekara.finalproject.app.product.enums;

import com.emrekara.finalproject.app.gen.enums.BaseErrorMessage;

public enum ProductErrorMessage implements BaseErrorMessage {

    PRODUCT_NAME_EMPTY_ERROR("Product NAME empty error!"),
    PRODUCT_TYPE_EMPTY_ERROR("Product TYPE empty error!"),
    PRODUCT_PRICE_NEGATIVE_ERROR("Product PRICE NEGATIVE error!"),
    PRODUCT_PRICE_NULL_ERROR("Product PRICE NULL error!"),
    PRODUCT_NOT_FOUND_ERROR("Product NOT FOUND error!"),
    ;

    private String message;

    ProductErrorMessage(String message) {
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
