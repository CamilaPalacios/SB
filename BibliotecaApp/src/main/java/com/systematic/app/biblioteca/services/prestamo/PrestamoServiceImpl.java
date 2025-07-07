package com.systematic.app.biblioteca.services.prestamo;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.prestamo.PrestamoDAO;
import com.systematic.app.biblioteca.dao.prestamo.PrestamoDAOImpl;
import com.systematic.app.biblioteca.models.Prestamo;
import java.sql.Connection;
import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    
    private final PrestamoDAO prestamoDAO;
    
    public PrestamoServiceImpl() {
        try {
            Connection conn = DBConnection.getConnection();
            this.prestamoDAO = new PrestamoDAOImpl(conn);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar PrestamoServiceImpl", e);
        }
    }

    @Override
    public List<Prestamo> listarTodos() {
        return prestamoDAO.listarTodos();
    }

    @Override
    public List<Prestamo> listarActivos() {
        return prestamoDAO.listarActivos();
    }

    @Override
    public Prestamo buscarPorId(Integer id) {
        return prestamoDAO.buscarPorId(id);
    }

    @Override
    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoDAO.crear(prestamo);
    }

    @Override
    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        return prestamoDAO.actualizar(prestamo);
    }

    @Override
    public void eliminarPrestamo(Integer id) {
        prestamoDAO.eliminar(id);
    }

    @Override
    public void registrarDevolucion(Integer idPrestamo) {
        prestamoDAO.registrarDevolucion(idPrestamo);
    }

    @Override
    public List<Prestamo> buscarPorUsuario(Integer idUsuario) {
        return prestamoDAO.buscarPorUsuario(idUsuario);
    }

    @Override
    public List<Prestamo> buscarPorLibro(Integer idLibro) {
        return prestamoDAO.buscarPorLibro(idLibro);
    }
}