package com.electrodostore.producto_service.service;

import com.electrodostore.producto_service.dto.ProductoOperacionStockDto;
import com.electrodostore.producto_service.dto.ProductoPatchRequestDto;
import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;
import com.electrodostore.producto_service.exception.ProductoNotFoundException;
import com.electrodostore.producto_service.exception.StockInsuficienteException;
import com.electrodostore.producto_service.model.Producto;
import com.electrodostore.producto_service.repository.IProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepo;

    public ProductoService(IProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    /**
     * Construye DTO de respuesta para exponer un producto
     * */
    private ProductoResponseDto buildProductoResponse(Producto objProducto) {
        return new ProductoResponseDto(
                objProducto.getId(),
                objProducto.getName(),
                objProducto.getStock(),
                objProducto.getPrice(),
                objProducto.getDescription()
        );
    }

    /**
     * Consulta un producto por su ID y lanza excepción si no existe
     */
    private Producto findProducto(Long id) {
       return productoRepo.findById(id)
               .orElseThrow(
                       () -> new ProductoNotFoundException("No se encontró producto con id: " + id)
               );
    }

    /**
     * Consulta los datos de una lista de productos
     * a partir de sus ids.
     */
    private List<Producto> findVariosProductos(List<Long> productosIds) {
        List<Producto> listProductos = new ArrayList<>();

        for(Long productoId: productosIds){
            listProductos.add(
                    findProducto(productoId)
            );
        }

        return listProductos;
    }

    /**
     * Extrae los ids de una lista de productos
     * para realizar operaciones sobre su stock.
     */
    private List<Long> getProductosIds(List<ProductoOperacionStockDto> productosOperarStock){
        List<Long> productosIds = new ArrayList<>();

        for(ProductoOperacionStockDto objProducto: productosOperarStock){
            productosIds.add(objProducto.productoId());
        }

        return productosIds;
    }

    /**
     * Valida que un string enviado como valor de un campo no esté vació ni lleno de espacios
     */
    private void validarNotBlank(String valor, String nombreCampo){
        if(valor.isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, nombreCampo + " no puede estar vacío"
            );
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> findAllProductos() {
        List<ProductoResponseDto> listProductos = new ArrayList<>();

        for (Producto objProducto : productoRepo.findByActiveTrue()) {
            listProductos.add(
                    buildProductoResponse(objProducto)
            );
        }

        return listProductos;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoResponseDto findProductoResponse(Long id) {
        return buildProductoResponse(
                findProducto(id)
        );
    }

    @Transactional
    @Override
    public ProductoResponseDto saveProducto(ProductoRequestDto objNuevo) {
        Producto objProducto = new Producto();

        objProducto.setName(objNuevo.name());
        objProducto.setStock(objNuevo.stock());
        objProducto.setPrice(objNuevo.price());
        objProducto.setDescription(objNuevo.description());

        productoRepo.save(objProducto);

        return buildProductoResponse(objProducto);
    }

    @Transactional
    @Override
    public void disableProducto(Long id) {
        Producto objProducto = findProducto(id);

        objProducto.setActive(false);
    }

    @Transactional
    @Override
    public ProductoResponseDto updateProducto(Long id, ProductoRequestDto objUpdated) {
        Producto objProducto = findProducto(id);

        //Modificación total de los datos del producto
        objProducto.setName(objUpdated.name());
        objProducto.setStock(objUpdated.stock());
        objProducto.setPrice(objUpdated.price());
        objProducto.setDescription(objUpdated.description());

        return buildProductoResponse(objProducto);
    }

    @Transactional
    @Override
    public ProductoResponseDto patchProducto(Long id, ProductoPatchRequestDto objUpdated) {
        Producto objProducto = findProducto(id);

        //Modificación parcial de los datos del producto
        if (objUpdated.name() != null) {
            validarNotBlank(objUpdated.name(), "el nombre");
            objProducto.setName(objUpdated.name());
        }

        if (objUpdated.stock() != null) {
            objProducto.setStock(objUpdated.stock());
        }

        if (objUpdated.price() != null) {
            objProducto.setPrice(objUpdated.price());
        }

        if (objUpdated.description() != null) {
            validarNotBlank(objUpdated.description(), "la descripción");
            objProducto.setDescription(objUpdated.description());
        }

        return buildProductoResponse(objProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> findProductosResponse(List<Long> productsIds) {

        List<Producto> listProductos = productoRepo.findByIdIn(productsIds);

        //Valida que se encontró al menos un producto de los solicitados
        if (listProductos.isEmpty()) {
            throw new ProductoNotFoundException("No se encontró ningún producto");
        }

        List<ProductoResponseDto> productosResponse = new ArrayList<>();

        for (Producto objProducto : listProductos) {
            productosResponse.add(
                    buildProductoResponse(objProducto));
        }

        return productosResponse;
    }


    @Override
    public void verificarStock(List<ProductoOperacionStockDto> productosValidarStock) {

        List<Producto> listProductos = findVariosProductos(
                getProductosIds(productosValidarStock)
        );

        /*Sincroniza cada producto que se mandó a validar con
        * su correspondiente registro en este servicio
        * para verificar si el stock es suficiente
        */
        for(ProductoOperacionStockDto productoValidarStock: productosValidarStock){

            for(Producto objProducto: listProductos){

                if(productoValidarStock.productoId().equals(objProducto.getId())){

                    if(objProducto.getStock() < productoValidarStock.cantidadOperar()){
                        throw new StockInsuficienteException(
                                "El producto con id: " + objProducto.getId() +
                                " no tiene suficiente stock para cubrir la cantidad: " + productoValidarStock.cantidadOperar()
                        );
                    }

                    //Deja de buscar cundo se encuentre el producto equivalente y se valide el stock
                    break;
                }
            }
        }

    }

    @Transactional
    @Override
    public void descontarStock(List<ProductoOperacionStockDto> productosDescontarStock) {
        //Valida stock de los productos antes de descontar
        verificarStock(productosDescontarStock);

        List<Producto> listProductos = findVariosProductos(
                getProductosIds(productosDescontarStock)
        );

        /*Sincroniza cada producto que se mandó a descontar con
         * su correspondiente registro en este servicio
         * para descontar el stock del producto.
         */
        for(ProductoOperacionStockDto productoDescontarStock: productosDescontarStock){

            for(Producto objProducto: listProductos){

                if(productoDescontarStock.productoId().equals(objProducto.getId())){

                    //Descuenta stock
                    objProducto.setStock(
                            objProducto.getStock() - productoDescontarStock.cantidadOperar()
                    );

                    //Para de buscar cuando se encuentre el producto equivalente y se descuente el stock
                    break;
                }
            }
        }

    }

    @Transactional
    @Override
    public void reponerStock(List<ProductoOperacionStockDto> productosReponerStock) {
        List<Producto> listProductos = findVariosProductos(
                getProductosIds(productosReponerStock)
        );

        /*Sincroniza cada producto que se mandó a reponer con
         * su correspondiente registro en este servicio
         * para reponer el stock del producto.
         */
        for(ProductoOperacionStockDto productoReponerStock: productosReponerStock){

            for(Producto objProducto: listProductos){

                if(productoReponerStock.productoId().equals(objProducto.getId())){

                    //Repone stock
                    objProducto.setStock(
                            objProducto.getStock() + productoReponerStock.cantidadOperar()
                    );

                    //Deja de buscar cuando se encuentre el producto equivalente y se reponga stock
                    break;
                }
            }
        }
    }
}
