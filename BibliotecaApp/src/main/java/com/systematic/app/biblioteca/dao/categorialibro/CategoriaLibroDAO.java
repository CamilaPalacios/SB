package com.systematic.app.biblioteca.dao.categorialibro;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public interface CategoriaLibroDAO extends CRUD<CategoriaLibro> {

    Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria);
    
}
