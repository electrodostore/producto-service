package com.electrodostore.producto_service.dto;

import java.math.BigDecimal;

//Clase Dto exclusiva para los datos que el cliente puede ingresar o modificar
public record ProductoRequestDto(
        String name,
        Integer stock,
        BigDecimal price,
        String description
) {

}
