package com.electrodostore.producto_service.exception;

import lombok.Getter;

@Getter  //-> Se exponen campo(s)

//Excepción personalizada para cuando un producto no tenga stock suficiente para una determinada operación
public class StockInsuficienteException extends RuntimeException{

    //ErrorCode para identificar esta excepción fuera del dominio de este servicio
    private final ProductoErrorCode errorCode;

    public StockInsuficienteException(String message){
        super(message);

        //Se le asigna el valor del code correspondiente a esta excepción
        this.errorCode = ProductoErrorCode.PRODUCT_STOCK_INSUFICIENTE;
    }
}
