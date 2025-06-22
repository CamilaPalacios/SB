package com.systematic.app.biblioteca.dao.cargo;

import com.systematic.app.biblioteca.models.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public class CargoDAOImpl implements CargoDAO {

    private Connection connection;

    public CargoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Cargo> findByNameCargo(String nameCargo) {
        String sql = "SELECT * FROM cargo WHERE nombreCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nameCargo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNombreCargo(rs.getString("nombreCargo"));
                    return Optional.of(cargo);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cargo> findById(Integer id) {
        String sql = "SELECT * FROM cargo WHERE idCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNombreCargo(rs.getString("nombreCargo"));
                    return Optional.of(cargo);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }

    @Override
    public List<Cargo> obtenerTodos() {
        List<Cargo> listaCargos = new ArrayList<>();
        String sql = "SELECT * FROM cargo";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNombreCargo(rs.getString("nombreCargo"));
                listaCargos.add(cargo);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listaCargos;
    }

    @Override
    public int insertar(Cargo cargo) {
        String sql = "INSERT INTO cargo(nombreCargo) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo.getNombreCargo());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int actualizar(Cargo cargo) {
        String sql = "UPDATE cargo SET nombreCargo=? WHERE idCargo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo.getNombreCargo());
            ps.setInt(2, cargo.getIdCargo());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM cargo WHERE idCargo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int totalRegistros() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM cargo";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return total;
    }

}
