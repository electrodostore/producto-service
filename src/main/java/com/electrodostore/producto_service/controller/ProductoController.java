package com.electrodostore.producto_service.controller;

import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;
import com.electrodostore.producto_service.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    //Inyección de dependencia por constructor para el service de Producto
    public ProductoController(IProductoService productoService){this.productoService = productoService;}

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> findAllProductos(){
        return ResponseEntity.ok(productoService.findAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> findProducto(@PathVariable Long id){
        return ResponseEntity.ok(productoService.findProductoResponse(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDto> saveProducto(@RequestBody ProductoRequestDto objNuevo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.saveProducto(objNuevo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> updateProducto(@PathVariable Long id, @RequestBody ProductoRequestDto objUpdated){
        return ResponseEntity.ok(productoService.updateProducto(id, objUpdated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> patchProducto(@PathVariable Long id, @RequestBody ProductoRequestDto objUpdated){
        return ResponseEntity.ok(productoService.patchProducto(id, objUpdated));
    }

    @PostMapping("/traer-productos-por-ids")
    public ResponseEntity<List<ProductoResponseDto>> findPorductos(@RequestBody List<Long> productosIds){
        return ResponseEntity.ok(productoService.findProductosResponse(productosIds));
    }
}
