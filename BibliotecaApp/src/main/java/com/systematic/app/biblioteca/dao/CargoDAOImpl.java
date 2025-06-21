package com.systematic.app.biblioteca.dao;

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
                    cargo.setId(rs.getInt("id"));
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
    public Optional<Cargo> finByUserId(Integer id) {
        String sql = "SELECT * FROM cargo WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setId(rs.getInt("id"));
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
                cargo.setId(rs.getInt("id"));
                cargo.setNombreCargo(rs.getString("nombreCargo"));
                listaCargos.add(cargo);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listaCargos;
    }

    @Override
    public void insertar(Cargo cargo) {
        String sql = "INSERT INTO cargo(nombreCargo) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo.getNombreCargo());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Insertar el Cargo");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void actualizar(Cargo cargo) {
        String sql = "UPDATE cargo SET nombreCargo=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo.getNombreCargo());
            ps.setInt(2, cargo.getId());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Actualizar el Cargo");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM cargo WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Eliminar el Cargo");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
