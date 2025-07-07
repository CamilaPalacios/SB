package com.systematic.app.biblioteca.services.dashboard;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.*;
import com.systematic.app.biblioteca.dao.autor.AutorDAO;
import com.systematic.app.biblioteca.dao.autor.AutorDAOImpl;
import com.systematic.app.biblioteca.dao.cargo.CargoDAO;
import com.systematic.app.biblioteca.dao.cargo.CargoDAOImpl;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAOImpl;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAO;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAOImpl;
import com.systematic.app.biblioteca.dao.libro.*;
import com.systematic.app.biblioteca.dao.prestamo.*;
import com.systematic.app.biblioteca.dao.usuario.*;
import com.systematic.app.biblioteca.models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardServiceImpl implements DashboardService {

    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    private final CategoriaLibroDAO categoriaDAO;
    private final AutorDAO autorDAO;
    private final EditorialDAO editorialDAO;
    private final CargoDAO cargoDAO;

    public DashboardServiceImpl() {
        try {
            Connection conn = DBConnection.getConnection();

            this.libroDAO = new LibroDAOImpl(conn);
            this.usuarioDAO = new UsuarioDAOImpl(conn);
            this.prestamoDAO = new PrestamoDAOImpl(conn);

            this.categoriaDAO = new CategoriaLibroDAOImpl(conn);
            this.autorDAO = new AutorDAOImpl(conn);
            this.editorialDAO = new EditorialDAOImpl(conn);
            this.cargoDAO = new CargoDAOImpl(conn);

        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar DAOs", e);
        }
    }

    @Override
    public EstadisticasDTO obtenerEstadisticasIniciales() {
        EstadisticasDTO stats = new EstadisticasDTO();

        stats.setTotalLibros(libroDAO.contar());
        stats.setLibrosDisponibles(libroDAO.contarDisponibles());
        stats.setLibrosPrestados(libroDAO.contarPrestados());

        stats.setTotalUsuarios(usuarioDAO.contar());

        stats.setPrestamosActivos(prestamoDAO.contarPrestamosActivos());
        stats.setPrestamosVencidos(prestamoDAO.contarPrestamosVencidos());

        stats.setTotalCategorias(categoriaDAO.contar());
        stats.setTotalAutores(autorDAO.contar());
        stats.setTotalEditoriales(editorialDAO.contar());
        stats.setTotalCargos(cargoDAO.contar());

        return stats;
    }

    @Override
    public int contarLibros() {
        return libroDAO.contar();
    }

    @Override
    public int contarUsuarios() {
        return usuarioDAO.contar();
    }

    @Override
    public int contarPrestamosActivos() {
        return prestamoDAO.contarPrestamosActivos();
    }

    @Override
    public int[] obtenerPrestamosPorMes() {
        int[] meses = new int[12];
        prestamoDAO.contarPrestamosPorMes().forEach(resultado -> {
            int mes = resultado.getMes() - 1;
            if (mes >= 0 && mes < 12) {
                meses[mes] = resultado.getTotal();
            }
        });
        return meses;
    }

    @Override
    public List<LibroPopularDTO> obtenerLibrosMasPrestados(int limit) {
        return libroDAO.obtenerLibrosMasPrestados(limit)
                .stream()
                .sorted(Comparator.comparingInt(LibroPopularDTO::getTotalPrestamos).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> obtenerPrestamosProximosAVencer(int dias) {
        return prestamoDAO.obtenerProximosAVencer(dias)
                .stream()
                .sorted(Comparator.comparing(Prestamo::getFechaDevolucionEstimada))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> obtenerPrestamosRecientes() {
        return prestamoDAO.obtenerPrestamosRecientes(5); // Asegúrate de que exista este método en PrestamoDAO
    }
}
