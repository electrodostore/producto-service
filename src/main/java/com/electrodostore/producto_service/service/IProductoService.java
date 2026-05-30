package com.electrodostore.producto_service.service;

import com.electrodostore.producto_service.dto.ProductoOperacionStockDto;
import com.electrodostore.producto_service.dto.ProductoPatchRequestDto;
import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;

import java.util.List;
import java.util.Set;

public interface IProductoService {

    List<ProductoResponseDto> findAllProductos();

    ProductoResponseDto findProductoResponse(Long id);

    /**
     * Busca un conjunto de productos por sus ids
     */
    List<ProductoResponseDto> findProductosResponse(List<Long> productsIds);

    ProductoResponseDto saveProducto(ProductoRequestDto objNuevo);

    /**
     * Borrado lógico para evitar perder datos históricos importantes
     */
    void disableProducto(Long id);

    ProductoResponseDto updateProducto(Long id, ProductoRequestDto objUpdated);

    ProductoResponseDto patchProducto(Long id, ProductoPatchRequestDto objUpdated);

    /**
     * Descuenta una cierta cantidad al stock de una lista de productos
     */
    void descontarStock(List<ProductoOperacionStockDto> productosDescontarStock);

    /**
     * Repone una cierta cantidad al stock de una lista de productos
     */
    void reponerStock(List<ProductoOperacionStockDto> productosReponerStock);

    /**
     * Verifica si el stock de una lista de productos es suficiente
     * para cubrir la cantidad que se desea comprar de estos.
     */
    void verificarStock(List<ProductoOperacionStockDto> productosValidarStock);
}
