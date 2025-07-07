package com.systematic.app.biblioteca.services.prestamo;

import com.systematic.app.biblioteca.models.Prestamo;
import java.util.List;

public interface PrestamoService {
    List<Prestamo> listarTodos();
    List<Prestamo> listarActivos();
    Prestamo buscarPorId(Integer id);
    Prestamo crearPrestamo(Prestamo prestamo);
    Prestamo actualizarPrestamo(Prestamo prestamo);
    void eliminarPrestamo(Integer id);
    void registrarDevolucion(Integer idPrestamo);
    List<Prestamo> buscarPorUsuario(Integer idUsuario);
    List<Prestamo> buscarPorLibro(Integer idLibro);
}