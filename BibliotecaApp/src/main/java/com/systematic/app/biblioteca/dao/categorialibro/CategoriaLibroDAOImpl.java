package com.systematic.app.biblioteca.dao.categorialibro;

import com.systematic.app.biblioteca.models.CategoriaLibro;
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
public class CategoriaLibroDAOImpl implements CategoriaLibroDAO {

    private Connection connection;

    public CategoriaLibroDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<CategoriaLibro> findByNameCategoria(String nombreCategoria) {
        String sql = "SELECT * FROM categorialibro WHERE nombreCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaLibro categoriaLibro = new CategoriaLibro();
                    categoriaLibro.setIdCategoria(rs.getInt("idCategoria"));
                    categoriaLibro.setNombreCategoria(rs.getString("nombreCategoria"));
                    return Optional.of(categoriaLibro);
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
    public Optional<CategoriaLibro> findById(Integer idCategoria) {
        String sql = "SELECT * FROM categorialibro WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaLibro categoriaLibro = new CategoriaLibro();
                    categoriaLibro.setIdCategoria(rs.getInt("idCategoria"));
                    categoriaLibro.setNombreCategoria(rs.getString("nombreCategoria"));
                    return Optional.of(categoriaLibro);
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
    public List<CategoriaLibro> obtenerTodos() {
        List<CategoriaLibro> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categorialibro";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaLibro categoriaLibro = new CategoriaLibro();
                categoriaLibro.setIdCategoria(rs.getInt("idCategoria"));
                categoriaLibro.setNombreCategoria(rs.getString("nombreCategoria"));
                listaCategorias.add(categoriaLibro);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listaCategorias;
    }

    @Override
    public int insertar(CategoriaLibro categoriaLibro) {
        String sql = "INSERT INTO categorialibro(nombreCategoria) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoriaLibro.getNombreCategoria());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int actualizar(CategoriaLibro categoriaLibro) {
        String sql = "UPDATE categorialibro SET nombreCategoria=? WHERE idCategoria=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoriaLibro.getNombreCategoria());
            ps.setInt(2, categoriaLibro.getIdCategoria());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM categorialibro WHERE idCategoria=?";
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
        String sql = "SELECT COUNT(*) FROM categorialibro";
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
