package com.electrodostore.producto_service.dto;

/*DTO cuya función será almacenar los datos relevantes cuando se vaya a hacer una operación (descontar, reponer, validar, etc.)
 sobre el stock de un determinado producto */
public record ProductoOperacionStockDto(
        Long productoId,
        Integer cantidadOperar
) {}
