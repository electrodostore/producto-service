package com.electrodostore.producto_service.repository;

import com.electrodostore.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Repositorio de datos para producto-service
 * */
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Consulta una lista de productos por sus ids.
     */
    List<Producto> findByIdIn(List<Long> ids);

    /**
     * Trae solo los productos activos desde la base de datos
     */
    List<Producto> findByActiveTrue();
}