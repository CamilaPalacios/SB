package com.systematic.app.biblioteca.services.categorialibro;

import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.util.List;
import java.util.Optional;

public interface CategorialibroService {
    List<CategoriaLibro> findAll();
    Optional<CategoriaLibro> findById(Integer id);
    Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria);
    void insertarCategoriaLibro(CategoriaLibro categoria);
    void actualizarCategoriaLibro(CategoriaLibro categoria);
    void eliminarCategoriaLibro(Integer id);
}
