package com.electrodostore.producto_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Centraliza el manejo de excepciones de la API
 * y devuelve una respuesta personalizada del error
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Construye el cuerpo de la respuesta de error.
     */
    private Map<String, Object> buildErrorMessage(HttpStatus status, String message, String errorCode){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("errorCode", errorCode);
        response.put("mensaje", message);

        return response;
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerProductoNotFound(ProductoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getErrorCode().name()));
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Map<String, Object>> handlerStockInsuficiente(StockInsuficienteException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getErrorCode().name()));
    }
}
