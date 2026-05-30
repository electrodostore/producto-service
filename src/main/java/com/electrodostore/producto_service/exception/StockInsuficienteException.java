package com.electrodostore.producto_service.exception;

import lombok.Getter;

/**
 * Excepción personalizada para indicar que el stock de un
 * producto no es suficiente para la operación que se desea realizar.
 */
@Getter
public class StockInsuficienteException extends RuntimeException{
    private final ProductoErrorCode errorCode;

    public StockInsuficienteException(String message){
        super(message);
        this.errorCode = ProductoErrorCode.PRODUCT_STOCK_INSUFICIENTE;
    }
}
