package com.electrodostore.producto_service.exception;

import lombok.Getter;

@Getter
public class ProductoNotFoundException extends RuntimeException{
    private final ProductoErrorCode errorCode;

    public ProductoNotFoundException(String message){
        super(message);
        this.errorCode = ProductoErrorCode.PRODUCT_NOT_FOUND;
    }
}
