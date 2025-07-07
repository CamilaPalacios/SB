package com.systematic.app.biblioteca.dao.autor;

import com.systematic.app.biblioteca.models.Autor;
import java.util.List;
import java.util.Optional;

public interface AutorDAO {
    Autor crearAutor(Autor autor);
    Optional<Autor> buscarPorId(Integer id);
    List<Autor> listarTodos();
    Autor actualizarAutor(Autor autor);
    void eliminarAutor(Integer id);
    List<Autor> buscarPorNombre(String nombre);
    int contar();

}
