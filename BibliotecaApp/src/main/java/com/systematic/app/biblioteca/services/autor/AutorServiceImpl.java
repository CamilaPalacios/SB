package com.systematic.app.biblioteca.services.autor;

import com.systematic.app.biblioteca.dao.autor.AutorDAO;
import com.systematic.app.biblioteca.models.Autor;

import java.util.List;
import java.util.Optional;

public class AutorServiceImpl implements AutorService {

    private final AutorDAO autorDAO;

    public AutorServiceImpl(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    @Override
    public Autor crearAutor(Autor autor) {
        // Aquí asumimos que el DAO devuelve el autor creado con ID
        return autorDAO.crearAutor(autor);
    }

    @Override
    public Optional<Autor> buscarPorId(Integer id) {
        return autorDAO.buscarPorId(id);
    }

    @Override
    public List<Autor> listarTodos() {
        return autorDAO.listarTodos();
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        return autorDAO.actualizarAutor(autor);
    }

    @Override
    public void eliminarAutor(Integer id) {
        autorDAO.eliminarAutor(id);
    }

    @Override
    public List<Autor> buscarPorNombre(String nombre) {
        return autorDAO.buscarPorNombre(nombre);
    }
}
