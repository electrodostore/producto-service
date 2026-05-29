package com.electrodostore.producto_service.dto;

import java.math.BigDecimal;

//Clase Dto exclusiva para datos que se pueden exponer al cliente
public record ProductoResponseDto(
        Long id,
        String name,
        Integer stock,
        BigDecimal price,
        String description,
        boolean active
) {
}
