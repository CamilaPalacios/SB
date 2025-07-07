package com.systematic.app.biblioteca.dao.editorial;

import com.systematic.app.biblioteca.models.Editorial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditorialDAOImpl implements EditorialDAO {

    private final Connection connection;

    public EditorialDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Editorial crear(Editorial editorial) {
        String sql = "INSERT INTO editorial (nombreEditorial) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, editorial.getNombreEditorial());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        editorial.setIdEditorial(rs.getInt(1));
                    }
                }
            }
            return editorial;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la editorial", e);
        }
    }

    @Override
    public Editorial actualizar(Editorial editorial) {
        String sql = "UPDATE editorial SET nombreEditorial = ? WHERE idEditorial = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, editorial.getNombreEditorial());
            ps.setInt(2, editorial.getIdEditorial());
            ps.executeUpdate();
            return editorial;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la editorial", e);
        }
    }

    @Override
    public boolean eliminar(Integer id) {
        String sql = "DELETE FROM editorial WHERE idEditorial = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la editorial", e);
        }
    }

    @Override
    public Optional<Editorial> findById(Integer id) {
        Editorial editorial = buscarPorId(id); // Usa tu método actual
        return Optional.ofNullable(editorial);
    }

    @Override
    public Editorial buscarPorId(Integer id) {
        String sql = "SELECT * FROM editorial WHERE idEditorial = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEditorial(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar editorial por ID", e);
        }
        return null;
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) FROM editoriales";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar editoriales", e);
        }
        return 0;
    }

    @Override
    public List<Editorial> listarTodos() {
        List<Editorial> lista = new ArrayList<>();
        String sql = "SELECT * FROM editorial ORDER BY nombreEditorial ASC";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearEditorial(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar editoriales", e);
        }
        return lista;
    }

    private Editorial mapearEditorial(ResultSet rs) throws SQLException {
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(rs.getInt("idEditorial"));
        editorial.setNombreEditorial(rs.getString("nombreEditorial"));
        return editorial;
    }
}
