package com.systematic.app.biblioteca.dao.editorial;

import com.systematic.app.biblioteca.models.Editorial;
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
public class EditorialDAOImpl implements EditorialDAO {

    private Connection connection;

    public EditorialDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Editorial> findByNameEditorial(String nombreEditorial) {
        String sql = "SELECT * FROM editorial WHERE nombreEditorial = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreEditorial);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Editorial editorial = new Editorial();
                    editorial.setIdEditorial(rs.getInt("id"));
                    editorial.setNombreEditorial(rs.getString("nombreEditorial"));
                    return Optional.of(editorial);
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
    public Optional<Editorial> findById(Integer id) {
        String sql = "SELECT * FROM editorial WHERE idEditorial = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Editorial editorial = new Editorial();
                    editorial.setIdEditorial(rs.getInt("idEditorial"));
                    editorial.setNombreEditorial(rs.getString("nombreEditorial"));
                    return Optional.of(editorial);
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
    public List<Editorial> obtenerTodos() {
        List<Editorial> listaEditoriales = new ArrayList<>();
        String sql = "SELECT * FROM editorial";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Editorial editorial = new Editorial();
                editorial.setIdEditorial(rs.getInt("idEditorial"));
                editorial.setNombreEditorial(rs.getString("nombreEditorial"));
                listaEditoriales.add(editorial);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listaEditoriales;
    }

    @Override
    public void insertar(Editorial editorial) {
        String sql = "INSERT INTO editorial(nombreEditorial) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, editorial.getNombreEditorial());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Insertar el Editorial");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void actualizar(Editorial editorial) {
        String sql = "UPDATE editorial SET nombreEditorial=? WHERE idEditorial=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, editorial.getNombreEditorial());
            ps.setInt(2, editorial.getIdEditorial());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Actualizar la Editorial");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM editorial WHERE idEditorial=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error al Eliminar la Editorial");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int totalRegistros() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM editorial";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return total;
    }

}
