package com.electrodostore.producto_service.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoPatchRequestDto(
        String name,
        @PositiveOrZero
        Integer stock,

        @DecimalMin(value = "0.01")
        @Digits(integer = 10, fraction = 2)
        BigDecimal price,

        String description
) {
}
