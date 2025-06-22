package com.systematic.app.biblioteca.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public interface CRUD<T> {

    /**
     * Busca una entidad por su ID.
     */
    Optional<T> findById(Integer id);

    /**
     * Devuelve una lista con todas las entidades.
     */
    List<T> obtenerTodos();

     /**
     * Inserta una nueva entidad.
     */
    int insertar(T t);
    
    /**
     * Actualiza una entidad existente.
     */
    int actualizar(T t);
    
    /**
     * Elimina una entidad por su ID.
     */
    int eliminar(Integer id);
    
    /**
     * Devuelve la cantidad total de registros.
     */
    int totalRegistros();
    
}
