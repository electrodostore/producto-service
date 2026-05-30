package com.electrodostore.producto_service.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequestDto(
        @NotBlank
        String name,

        @NotNull
        @PositiveOrZero
        Integer stock,

        @NotNull
        @DecimalMin(value = "0.01")
        @Digits(integer = 10, fraction = 2)
        BigDecimal price,

        @NotBlank
        String description
) {}
