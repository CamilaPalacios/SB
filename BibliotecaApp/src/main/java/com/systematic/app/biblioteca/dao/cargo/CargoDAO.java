package com.systematic.app.biblioteca.dao.cargo;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.Cargo;
import java.util.Optional;

public interface CargoDAO extends CRUD<Cargo, Integer> {

    Optional<Cargo> findByNameCargo(String nameCargo);
    boolean tieneUsuariosAsociados(Integer idCargo);


    int totalRegistros();
    int contar();

}

