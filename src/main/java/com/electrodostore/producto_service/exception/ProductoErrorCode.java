package com.electrodostore.producto_service.exception;

/*Enum que define los diferentes valores de errorCode que pueden tener las excepciones personalizadas para que puedan ser
 identificadas en otro servicio*/
public enum ProductoErrorCode {

    //Se define el valor del errorCode de las excepciones
    PRODUCT_NOT_FOUND, //ProductoNotFoundException
    PRODUCT_STOCK_INSUFICIENTE  //StockInsuficienteException
}
