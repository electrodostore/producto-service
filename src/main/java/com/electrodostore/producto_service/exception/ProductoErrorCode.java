package com.electrodostore.producto_service.exception;

/**
 * Códigos de error que identifican cada excepción de la API
 * fuera del dominio.
 */
public enum ProductoErrorCode {

    PRODUCT_NOT_FOUND, //ProductoNotFoundException
    PRODUCT_STOCK_INSUFICIENTE  //StockInsuficienteException
}
