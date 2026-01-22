package com.electrodostore.producto_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter
@AllArgsConstructor
@NoArgsConstructor
//Clase Dto exclusiva para datos que se pueden exponer al cliente
public class ProductoResponseDto {

    private Long id;
    private String name;
    private Integer stock;
    private Double price;
    private String description;
}
