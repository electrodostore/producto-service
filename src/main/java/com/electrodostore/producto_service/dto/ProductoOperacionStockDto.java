package com.electrodostore.producto_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Transporta los datos de los productos de forma centralizada
 * y reutilizable cuando se desea hacer
 * una operación con el stock de estos.
 */
public record ProductoOperacionStockDto(
        @NotNull Long productoId,
        @NotNull @PositiveOrZero Integer cantidadOperar
) {}
