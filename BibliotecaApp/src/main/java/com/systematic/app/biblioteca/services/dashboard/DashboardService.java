package com.systematic.app.biblioteca.services.dashboard;

import com.systematic.app.biblioteca.models.*;

import java.util.List;

public interface DashboardService {
    EstadisticasDTO obtenerEstadisticasIniciales();

    int contarLibros();
    int contarUsuarios();
    int contarPrestamosActivos();

    int[] obtenerPrestamosPorMes();

    List<LibroPopularDTO> obtenerLibrosMasPrestados(int limit);

    List<Prestamo> obtenerPrestamosProximosAVencer(int dias);

    List<Prestamo> obtenerPrestamosRecientes();
}
