package com.systematic.app.biblioteca.services.cargo;

import com.systematic.app.biblioteca.models.Cargo;
import java.util.List;
import java.util.Optional;

public interface CargoService {
    Cargo crearCargo(Cargo cargo);
    Optional<Cargo> buscarPorId(Integer id);
    List<Cargo> listarTodos();
    Cargo actualizarCargo(Cargo cargo);
    void eliminarCargo(Integer id);
    Optional<Cargo> buscarPorNombre(String nombre);
    boolean tieneUsuariosAsociados(Integer idCargo);

}