package com.systematic.app.biblioteca.services.categorialibro;

import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public class CategoriaLibroServiceImpl implements CategoriaLibroService {

    private final CategoriaLibroDAO categoriaLibroDAO;

    public CategoriaLibroServiceImpl(CategoriaLibroDAO categoriaLibroDAO) {
        this.categoriaLibroDAO = categoriaLibroDAO;
    }

    @Override
    public Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria) {
        return categoriaLibroDAO.findByNameCategoria(nombreCategoria);
    }

    @Override
    public Optional<CategoriaLibro> findById(Integer id) {
        return categoriaLibroDAO.findById(id);
    }

    @Override
    public List<CategoriaLibro> findAll() {
        return categoriaLibroDAO.obtenerTodos();
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
