package com.electrodostore.producto_service.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

//Clase Dto exclusiva para los datos que el cliente puede ingresar o modificar
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
