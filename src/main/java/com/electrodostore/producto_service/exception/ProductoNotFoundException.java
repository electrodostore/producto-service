package com.electrodostore.producto_service.exception;

import lombok.Getter;

@Getter  //-> Se exponen campo(s)
//Clase representativa de la excepción personalizada que se usa cuando no se logra encontrar un determinado producto
public class ProductoNotFoundException extends RuntimeException{

    //ErrorCode que identifica esta excepción cuando es lanzada fuera del dominio de este servicio
    private final ProductoErrorCode errorCode;

    /*Se lanza el mensaje de la excepción a la superclase "RuntimeException" de la cual se hereda para que
    esta me lo exponga después en el método "getMessage"*/
    public ProductoNotFoundException(String message){
        super(message);

        //Se le define el code correspondiente de la lista de valores en ProductoErrorCode
        this.errorCode = ProductoErrorCode.PRODUCT_NOT_FOUND;
    }
}
