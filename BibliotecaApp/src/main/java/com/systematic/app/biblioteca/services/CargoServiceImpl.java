
package com.systematic.app.biblioteca.services;

import com.systematic.app.biblioteca.dao.cargo.CargoDAO;
import com.systematic.app.biblioteca.models.Cargo;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public class CargoServiceImpl implements CargoService{
    
    private CargoDAO cargoDAO;
    
    private Connection connection;

    public CargoServiceImpl(CargoDAO cargoDAO, Connection connection) {
        this.cargoDAO = cargoDAO;
        this.connection = connection;
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
