package com.electrodostore.producto_service.exception;

//Clase representativa de la excepción personalizada que se usa cuando no se logra encontrar un determinado producto
public class ProductoNotFoundException extends RuntimeException{

    /*Se lanza el mensaje de la excepción a la superclase "RuntimeException" de la cual se hereda para que
    esta me lo exponga después en el método "getMessage"*/
    public ProductoNotFoundException(String message){
        super(message);
    }
}
