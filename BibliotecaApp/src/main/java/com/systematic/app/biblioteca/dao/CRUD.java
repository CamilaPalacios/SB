
package com.systematic.app.biblioteca.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public interface CRUD<T> {
    Optional<T> finByUserId(Integer id);
    List<T> obtenerTodos();
    void insertar(T t);
    void actualizar(T t);
    void eliminar(Integer id);
}
