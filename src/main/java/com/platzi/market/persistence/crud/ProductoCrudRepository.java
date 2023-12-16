package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Long> {

    //Se pueden usar querys de SQL
    //@Query(value = "SELECT * FROM productos WHERE id_categoria= ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(long idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
