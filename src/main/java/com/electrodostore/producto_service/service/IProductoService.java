package com.electrodostore.producto_service.service;

import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;

import java.util.List;

//Interfaz que define la lógica de negocio asociada a la gestión de productos
public interface IProductoService {

    //Traer todos los productos
    List<ProductoResponseDto> findAllProductos();

    //Traer un producto determinado por su id
    ProductoResponseDto findProductoResponse(Long id);

    ProductoResponseDto saveProducto(ProductoRequestDto objNuevo);

    void deleteProducto(Long id);

    //Modificación completa del registro Producto
    ProductoResponseDto updateProducto(Long id, ProductoRequestDto objUpdated);

    //Modificación parcial (Solo lo solicitado) del registro Producto
    ProductoResponseDto patchProducto(Long id, ProductoRequestDto objUpdated);
}
