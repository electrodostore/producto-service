package com.electrodostore.producto_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/*DTO cuya función será almacenar los datos relevantes cuando se vaya a hacer una operación (descontar, reponer, validar, etc.)
 sobre el stock de un determinado producto */
public record ProductoOperacionStockDto(
        @NotNull Long productoId,
        @NotNull @PositiveOrZero Integer cantidadOperar
) {}
