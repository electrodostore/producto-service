package com.electrodostore.producto_service.service;

import com.electrodostore.producto_service.dto.ProductoRequestDto;
import com.electrodostore.producto_service.dto.ProductoResponseDto;
import com.electrodostore.producto_service.exception.ProductoNotFoundException;
import com.electrodostore.producto_service.exception.StockInsuficienteException;
import com.electrodostore.producto_service.model.Producto;
import com.electrodostore.producto_service.repository.IProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    private final IProductoRepository productoRepo;

    //Inyección de dependencia por constructor para el repositorio de Producto
    public ProductoService(IProductoRepository productoRepo){this.productoRepo = productoRepo;}

    //Método propio para preparar a un objeto Producto para la exposición al cliente
    private ProductoResponseDto buildProductoResponse(Producto objProducto){
        return new ProductoResponseDto(objProducto.getId(), objProducto.getName(), objProducto.getStock(),
                objProducto.getPrice(), objProducto.getDescription());
    }

    /*Método propio para traer un registro de Producto que se usará exclusivamente para operaciones
     internas, ya que no está preparado para exponerse al cliente*/
    private Producto findProducto(Long id){
        //El objeto viene dentro de un optional para evitar el null
        Optional<Producto> objProducto = productoRepo.findById(id);

        //Si el optional viene vació, quiere decir que no existe registro con ese ID --> Excepción
        if(objProducto.isEmpty()){throw new ProductoNotFoundException("No se encontró producto con id: " + id);}

        return objProducto.get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> findAllProductos() {
        List<ProductoResponseDto> listProductos = new ArrayList<>();

        //Vamos preparando cada producto para su posterior exposición
        for(Producto objProducto: productoRepo.findAll()){
            listProductos.add(buildProductoResponse(objProducto));
        }

        return listProductos;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoResponseDto findProductoResponse(Long id) {
        //Basta solo con preparar el objeto que viene en findProducto para exponerlo al cliente
        return buildProductoResponse(findProducto(id));
    }

    @Transactional
    @Override
    public ProductoResponseDto saveProducto(ProductoRequestDto objNuevo) {
        Producto objProducto = new Producto();

        //Transferencia de datos para posteriormente crear el registro
        objProducto.setName(objNuevo.getName());
        objProducto.setStock(objNuevo.getStock());
        objProducto.setPrice(objNuevo.getPrice());
        objProducto.setDescription(objNuevo.getDescription());

        productoRepo.save(objProducto);

        return buildProductoResponse(objProducto);
    }

    @Transactional
    @Override
    public void deleteProducto(Long id) {
        Producto objProducto = findProducto(id);

        productoRepo.delete(objProducto);
    }

    @Transactional
    @Override
    public ProductoResponseDto updateProducto(Long id, ProductoRequestDto objUpdated) {
        Producto objProducto = findProducto(id);

        //Modificación de datos del registro solicitado
        objProducto.setName(objUpdated.getName());
        objProducto.setStock(objUpdated.getStock());
        objProducto.setPrice(objUpdated.getPrice());
        objProducto.setDescription(objUpdated.getDescription());

        //Guardamos cambios
        productoRepo.save(objProducto);

        return buildProductoResponse(objProducto);
    }

    @Transactional
    @Override
    public ProductoResponseDto patchProducto(Long id, ProductoRequestDto objUpdated) {
        Producto objProducto = findProducto(id);

        //Modificación parcial SOLO de los datos que se enviaron en "objUpdated"
        if(objUpdated.getName() != null){objProducto.setName(objUpdated.getName());}
        if(objUpdated.getStock() != null){objProducto.setStock(objUpdated.getStock());}
        if(objUpdated.getPrice() != null){objProducto.setPrice(objUpdated.getPrice());}
        if(objUpdated.getDescription() != null){objProducto.setDescription(objUpdated.getDescription());}

        //Guardamos cambios
        productoRepo.save(objProducto);

        return buildProductoResponse(objProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> findProductosResponse(List<Long> productsIds) {
        //Si no se encuentra ningún producto registrado con los ids indicados -> NOT_FOUND
        //Si no hay ningún ID con el que buscar -> NOT_FOUND
        if(productoRepo.findByIdIn(productsIds).isEmpty()){throw new ProductoNotFoundException("No se encontró ningún producto");}

        //Lista de los productos que se van a exponer como Response
        List<ProductoResponseDto> saleProducts = new ArrayList<>();

        //Vamos transfiriendo los datos que vienen desde la base de datos a los DTO que serán expuestos al cliente
        for(Producto objProducto: productoRepo.findByIdIn(productsIds)){
            saleProducts.add(buildProductoResponse(objProducto));
        }

        return saleProducts;
    }

    @Transactional
    @Override
    public void descontarStock(Long productoId, Integer cantidadDescontar) {
        //Buscamos Producto para descartar inexistencia
        Producto objProducto = findProducto(productoId);

        //Verificamos si tenemos stock suficiente para descontar
        if(cantidadDescontar > objProducto.getStock()){throw new StockInsuficienteException("Stock insuficiente");}

        //Si llegamos a este punto, descontamos
        objProducto.setStock(objProducto.getStock() - cantidadDescontar);

        productoRepo.save(objProducto);
    }

    @Transactional
    @Override
    public void reponerStock(Long productoId, Integer cantidadReponer) {
        //Buscamos Producto para descartar inexistencia
        Producto objProducto = findProducto(productoId);

        //reponemos cantidad
        objProducto.setStock(objProducto.getStock() + cantidadReponer);

        productoRepo.save(objProducto);
    }
}
