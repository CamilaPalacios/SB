
package com.systematic.app.biblioteca.services.categorialibro;

import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public interface CategoriaLibroService {
    Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria);
    Optional<CategoriaLibro> findById(Integer id);
    List<CategoriaLibro> findAll();
    void insertarCategoriaLibro(CategoriaLibro categoria);
    void actualizarCategoriaLibro(CategoriaLibro categoria);
    void eliminarCategoriaLibro(Integer id);
}
