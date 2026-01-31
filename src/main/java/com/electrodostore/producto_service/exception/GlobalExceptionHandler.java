package com.electrodostore.producto_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/*Clase global manejadora de excepciones.
Cuando ocurra una excepción, Spring verá que hay una clase marcada con la annotation: @RestControllerAdvice
por lo que la revisará para encontrar algún manejador o handler que maneje la excepción correspondiente*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Mensaje de error centralizado para cada excepción
    private Map<String, Object> exceptionErrorMessage(HttpStatus status, String message, String errorCode){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        //Identificador de cada excepción personalizada en otro servicio
        response.put("errorCode", errorCode);
        response.put("mensaje", message);

        return response;
    }

    //Manejador de la excepción ProductoNotFound
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerProductoNotFound(ProductoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getErrorCode().name()));
    }
}
