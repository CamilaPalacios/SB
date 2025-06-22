
package com.systematic.app.biblioteca.services.cargo;

import com.systematic.app.biblioteca.models.Cargo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public interface CargoService {
    Optional<Cargo> findByNameCargo(String nombreCargo);
    Optional<Cargo> findById(Integer id);
    List<Cargo> findAll();
    void insertCargo(Cargo cargo);
    void updateCargo(Cargo cargo);
    void deleteCargo(Integer id);
}
