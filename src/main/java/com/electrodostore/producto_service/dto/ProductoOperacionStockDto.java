package com.electrodostore.producto_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter
@AllArgsConstructor
@NoArgsConstructor
/*DTO cuya función será almacenar los datos relevantes cuando se vaya a hacer una operación (descontar, reponer, validar, etc.)
 sobre el stock de un determinado producto */
public class ProductoOperacionStockDto {

    private Long productoId;
    private Integer cantidadOperar; //Cantidad que se va a operar sobre el stock del producto
}
