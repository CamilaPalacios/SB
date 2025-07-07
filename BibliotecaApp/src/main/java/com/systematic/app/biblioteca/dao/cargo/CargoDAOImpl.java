package com.systematic.app.biblioteca.dao.cargo;

import com.systematic.app.biblioteca.models.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoDAOImpl implements CargoDAO {

    private final Connection connection;

    public CargoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Cargo> findById(Integer id) {
        String sql = "SELECT * FROM cargo WHERE idCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapCargo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cargo por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean tieneUsuariosAsociados(Integer idCargo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE idCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCargo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar usuarios asociados al cargo", e);
        }
        return false;
    }

    @Override
    public List<Cargo> obtenerTodos() {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargo";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cargos.add(mapCargo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar cargos", e);
        }
        return cargos;
    }

    @Override
    public Integer insertar(Cargo cargo) {
        if (cargo.getNombreCargo() == null || cargo.getNombreCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo no puede estar vacío");
        }

        String sql = "INSERT INTO cargo(nombreCargo) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cargo.getNombreCargo());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // 🔁 Devuelve el ID generado
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cargo", e);
        }
    }

    @Override
    public int actualizar(Cargo cargo) {
        if (cargo.getIdCargo() == null || cargo.getNombreCargo() == null || cargo.getNombreCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Datos del cargo inválidos");
        }
        String sql = "UPDATE cargo SET nombreCargo = ? WHERE idCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo.getNombreCargo());
            ps.setInt(2, cargo.getIdCargo());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cargo", e);
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM cargo WHERE idCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cargo", e);
        }
    }

    @Override
    public Optional<Cargo> findByNameCargo(String nameCargo) {
        String sql = "SELECT * FROM cargo WHERE nombreCargo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nameCargo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapCargo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cargo por nombre", e);
        }
        return Optional.empty();
    }

    @Override
    public int totalRegistros() {
        String sql = "SELECT COUNT(*) FROM cargo";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar cargos", e);
        }
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) FROM cargos";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar cargos", e);
        }
        return 0;
    }

    private Cargo mapCargo(ResultSet rs) throws SQLException {
        Cargo cargo = new Cargo();
        cargo.setIdCargo(rs.getInt("idCargo"));
        cargo.setNombreCargo(rs.getString("nombreCargo"));
        return cargo;
    }
}
