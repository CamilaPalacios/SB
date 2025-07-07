package com.systematic.app.biblioteca.dao.categorialibro;

import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.util.List;
import java.util.Optional;

public interface CategoriaLibroDAO {
    List<CategoriaLibro> listarTodos();
    CategoriaLibro obtenerPorId(int id);
    boolean insertar(CategoriaLibro categoria);
    boolean actualizar(CategoriaLibro categoria);
    boolean eliminar(int id);
    boolean existeCategoria(String nombre);
    boolean tieneLibrosAsociados(int id);
    
    // 🔴 Agrega esta línea si no la tienes
    List<CategoriaLibro> buscarPorNombre(String nombre);
    Optional<CategoriaLibro> findById(int id);
    int contar();

}
