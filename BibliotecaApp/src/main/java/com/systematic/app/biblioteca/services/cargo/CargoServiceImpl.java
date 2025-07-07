
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
    

    public Optional<Cargo> findByNameCargo(String nombreCargo) {
       return cargoDAO.findByNameCargo(nombreCargo);
    }

    public Optional<Cargo> findById(Integer id) {
       return cargoDAO.findById(id);
    }

    public List<Cargo> findAll() {
        return cargoDAO.obtenerTodos();
    }

    public void insertCargo(Cargo cargo) {
       cargoDAO.insertar(cargo);
    }

    public void updateCargo(Cargo cargo) {
       cargoDAO.actualizar(cargo);
    }

    public void deleteCargo(Integer id) {
       cargoDAO.eliminar(id);
    }

    @Override
    public Cargo crearCargo(Cargo cargo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Cargo> buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cargo> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cargo actualizarCargo(Cargo cargo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCargo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Cargo> buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
public boolean tieneUsuariosAsociados(Integer idCargo) {
    return cargoDAO.tieneUsuariosAsociados(idCargo);
}

    
}
