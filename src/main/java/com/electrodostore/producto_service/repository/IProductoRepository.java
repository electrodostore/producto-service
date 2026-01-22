package com.electrodostore.producto_service.repository;

import com.electrodostore.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface que hereda la clase de Spring-Data-Jpa que nos proporciona una cantidad de métodos útiles para
// la persistencia de datos
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}