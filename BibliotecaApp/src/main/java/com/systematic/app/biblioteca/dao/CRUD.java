package com.systematic.app.biblioteca.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones CRUD (Create, Read, Update, Delete)
 * @param <T> Tipo de entidad con la que trabajará el DAO
 * @param <ID> Tipo del identificador de la entidad (normalmente Integer o Long)
 */
public interface CRUD<T, ID> {
    
    /**
     * Inserta una nueva entidad en la base de datos
     * @param entidad La entidad a insertar
     * @return El ID de la entidad insertada o el número de filas afectadas
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    ID insertar(T entidad) throws RuntimeException;
    
    /**
     * Actualiza una entidad existente en la base de datos
     * @param entidad La entidad con los datos actualizados
     * @return El número de filas afectadas
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    int actualizar(T entidad) throws RuntimeException;
    
    /**
     * Elimina una entidad de la base de datos
     * @param id El ID de la entidad a eliminar
     * @return El número de filas afectadas
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    int eliminar(ID id) throws RuntimeException;
    
    /**
     * Obtiene una entidad por su ID
     * @param id El ID de la entidad a buscar
     * @return Un Optional que contiene la entidad si se encuentra, o vacío si no
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    Optional<T> findById(ID id) throws RuntimeException;
    
    /**
     * Obtiene todas las entidades de este tipo
     * @return Una lista con todas las entidades
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    List<T> obtenerTodos() throws RuntimeException;
    
    /**
     * Cuenta el número total de registros de esta entidad
     * @return El número total de registros
     * @throws RuntimeException Si ocurre un error durante la operación
     */
    int totalRegistros() throws RuntimeException;
}