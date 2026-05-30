package com.electrodostore.producto_service.controller;

import com.electrodostore.producto_service.dto.ProductoOperacionStockDto;
import com.electrodostore.producto_service.dto.ProductoPatchRequestDto;
import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;
import com.electrodostore.producto_service.service.IProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

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
    public ResponseEntity<ProductoResponseDto> createProducto(@RequestBody @Valid ProductoRequestDto objNuevo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.saveProducto(objNuevo));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disableProducto(@PathVariable Long id){
        productoService.disableProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> updateProducto(@PathVariable Long id, @RequestBody @Valid ProductoRequestDto objUpdated){
        return ResponseEntity.ok(productoService.updateProducto(id, objUpdated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> patchProducto(@PathVariable Long id, @RequestBody @Valid ProductoPatchRequestDto objUpdated){
        return ResponseEntity.ok(productoService.patchProducto(id, objUpdated));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductoResponseDto>> findProductos(@RequestBody @NotEmpty List<@NotNull Long> productosIds){
        return ResponseEntity.ok(productoService.findProductosResponse(productosIds));
    }

    @PatchMapping("/stock/descontar")
    public ResponseEntity<Void> descontarStock(@RequestBody @NotEmpty List<@NotNull @Valid ProductoOperacionStockDto> productosDescontarStock){
        productoService.descontarStock(productosDescontarStock);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/stock/reponer")
    public ResponseEntity<Void> reponerStock(@RequestBody @NotEmpty List<@NotNull @Valid ProductoOperacionStockDto> productosReponerStock){
        productoService.reponerStock(productosReponerStock);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/stock/verificar")
    public ResponseEntity<Void> verificarStockProducto(@RequestBody @NotEmpty List<@NotNull @Valid ProductoOperacionStockDto> productosValidarStock){
        productoService.verificarStock(productosValidarStock);
        return ResponseEntity.noContent().build();
    }

}
