
package com.systematic.app.biblioteca.services.cargo;

import com.systematic.app.biblioteca.dao.cargo.CargoDAO;
import com.systematic.app.biblioteca.models.Cargo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public class CargoServiceImpl implements CargoService{
    
    private final CargoDAO cargoDAO;
    
    public CargoServiceImpl(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }
    

    @Override
    public Optional<Cargo> findByNameCargo(String nombreCargo) {
       return cargoDAO.findByNameCargo(nombreCargo);
    }

    @Override
    public Optional<Cargo> findById(Integer id) {
       return cargoDAO.findById(id);
    }

    @Override
    public List<Cargo> findAll() {
        return cargoDAO.obtenerTodos();
    }

    @Override
    public void insertCargo(Cargo cargo) {
       cargoDAO.insertar(cargo);
    }

    @Override
    public void updateCargo(Cargo cargo) {
       cargoDAO.actualizar(cargo);
    }

    @Override
    public void deleteCargo(Integer id) {
       cargoDAO.eliminar(id);
    }
    
}
