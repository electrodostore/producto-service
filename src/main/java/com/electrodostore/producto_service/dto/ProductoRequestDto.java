package com.electrodostore.producto_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Clase Dto exclusiva para los datos que el cliente puede ingresar o modificar
public class ProductoRequestDto {

    private String name;
    private Integer stock;
    private Double price;
    private String description;

}
