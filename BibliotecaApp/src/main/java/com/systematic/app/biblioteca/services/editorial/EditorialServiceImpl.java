package com.systematic.app.biblioteca.services.editorial;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAO;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAOImpl;
import com.systematic.app.biblioteca.models.Editorial;

import java.sql.Connection;
import java.util.List;

public class EditorialServiceImpl implements EditorialService {

    private final EditorialDAO editorialDAO;

    public EditorialServiceImpl() {
        try {
            Connection conn = DBConnection.getConnection();
            this.editorialDAO = new EditorialDAOImpl(conn);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar EditorialServiceImpl", e);
        }
    }

    @Override
    public Editorial crearEditorial(Editorial editorial) {
        return editorialDAO.crear(editorial);
    }

    @Override
    public Editorial actualizarEditorial(Integer id, Editorial editorialActualizada) {
        Editorial existente = editorialDAO.buscarPorId(id);
        if (existente == null) {
            throw new RuntimeException("Editorial no encontrada con ID: " + id);
        }
        existente.setNombreEditorial(editorialActualizada.getNombreEditorial());
        return editorialDAO.actualizar(existente);
    }

    @Override
    public boolean eliminarEditorial(Integer id) {
        return editorialDAO.eliminar(id);
    }

    @Override
    public Editorial buscarEditorialPorId(Integer id) {
        return editorialDAO.buscarPorId(id);
    }

    @Override
    public List<Editorial> listarEditoriales() {
        return editorialDAO.listarTodos();
    }
}
