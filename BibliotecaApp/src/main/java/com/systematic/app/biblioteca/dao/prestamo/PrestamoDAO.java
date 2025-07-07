package com.systematic.app.biblioteca.dao.prestamo;

import com.systematic.app.biblioteca.models.Prestamo;
import com.systematic.app.biblioteca.models.PrestamosPorMesDTO;

import java.util.List;

public interface PrestamoDAO {
    List<Prestamo> listarTodos();
    List<Prestamo> listarActivos();
    Prestamo buscarPorId(Integer id);
    Prestamo crear(Prestamo prestamo);
    Prestamo actualizar(Prestamo prestamo);
    void eliminar(Integer id);
    void registrarDevolucion(Integer idPrestamo);
    List<Prestamo> buscarPorUsuario(Integer idUsuario);
    List<Prestamo> buscarPorLibro(Integer idLibro);
    List<Prestamo> listarVencidos();
    int contarPrestamosActivos();
    List<PrestamosPorMesDTO> contarPrestamosPorMes();
    List<Prestamo> obtenerProximosAVencer(int dias);
    int contarPrestamosVencidos();
    List<Prestamo> obtenerPrestamosRecientes(int limit);

}
