
package com.systematic.app.biblioteca.dao.autor;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.Autor;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public interface AutorDAO extends CRUD<Autor>{
    Optional<Autor> findByNameAutor(String nombreAutor);
}
