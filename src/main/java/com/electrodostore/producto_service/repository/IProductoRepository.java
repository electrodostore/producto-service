package com.electrodostore.producto_service.repository;

import com.electrodostore.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

//Interface que hereda la clase de Spring-Data-Jpa que nos proporciona una cantidad de métodos útiles para
// la persistencia de datos
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    /*Método para treaer una lista de productos buscados por sus ids.
    NOTA: El nombre debe seguir la estructura <accion>By<propiedad><operador> para que SpringData lo intrerprete correctamente
    NOTA: Cuando se espera retornar una lista de algunos registros, el IN como operador es obligatorio*/
    List<Producto> findByIdIn(Set<Long> ids);
}