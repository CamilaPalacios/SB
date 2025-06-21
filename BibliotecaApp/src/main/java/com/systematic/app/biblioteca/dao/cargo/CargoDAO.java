
package com.systematic.app.biblioteca.dao.cargo;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.Cargo;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public interface CargoDAO extends CRUD<Cargo>{
    Optional<Cargo> findByNameCargo(String nameCargo);
}
