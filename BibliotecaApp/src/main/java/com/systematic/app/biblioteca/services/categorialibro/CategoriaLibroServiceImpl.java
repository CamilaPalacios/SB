package com.systematic.app.biblioteca.services.categorialibro;

import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.models.CategoriaLibro;

import java.util.List;
import java.util.Optional;

public class CategorialibroServiceImpl implements CategorialibroService {

    private final CategoriaLibroDAO categoriaLibroDAO;

    public CategorialibroServiceImpl(CategoriaLibroDAO categoriaLibroDAO) {
        this.categoriaLibroDAO = categoriaLibroDAO;
    }

    @Override
    public List<CategoriaLibro> findAll() {
        return categoriaLibroDAO.listarTodos();
    }

    @Override
    public Optional<CategoriaLibro> findById(Integer id) {
        return categoriaLibroDAO.findById(id);
    }

    @Override
    public Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria) {
        List<CategoriaLibro> categorias = categoriaLibroDAO.buscarPorNombre(nombreCategoria);
        if (categorias != null && !categorias.isEmpty()) {
            return Optional.of(categorias.get(0));
        }
        return Optional.empty();
    }

    @Override
    public void insertarCategoriaLibro(CategoriaLibro categoria) {
        categoriaLibroDAO.insertar(categoria);
    }

    @Override
    public void actualizarCategoriaLibro(CategoriaLibro categoria) {
        categoriaLibroDAO.actualizar(categoria);
    }

    @Override
    public void eliminarCategoriaLibro(Integer id) {
        categoriaLibroDAO.eliminar(id);
    }
}
