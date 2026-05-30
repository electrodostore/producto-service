package com.electrodostore.producto_service.dto;

import java.math.BigDecimal;

public record ProductoResponseDto(
        Long id,
        String name,
        Integer stock,
        BigDecimal price,
        String description,
        boolean active
) {
}
