package com.systematic.app.biblioteca.dao.autor;

import com.systematic.app.biblioteca.models.Autor;
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
public class AutorDAOImpl implements AutorDAO {

    private Connection connection;

    public AutorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Autor> findByNameAutor(String nombreAutor) {
        String sql = "SELECT * FROM autor WHERE nombreAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreAutor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor();
                    autor.setIdAutor(rs.getInt("idAutor"));
                    autor.setNombreAutor(rs.getString("nombreAutor"));
                    return Optional.of(autor);
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
    public Optional<Autor> findById(Integer id) {
        String sql = "SELECT * FROM autor WHERE idAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor();
                    autor.setIdAutor(rs.getInt("idAutor"));
                    autor.setNombreAutor(rs.getString("nombreAutor"));
                    return Optional.of(autor);
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
    public List<Autor> obtenerTodos() {
        List<Autor> listaAutores = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("idAutor"));
                autor.setNombreAutor(rs.getString("nombreAutor"));
                listaAutores.add(autor);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listaAutores;
    }

    @Override
    public int insertar(Autor autor) {
        String sql = "INSERT INTO autor(nombreAutor) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, autor.getNombreAutor());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int actualizar(Autor autor) {
        String sql = "UPDATE autor SET nombreAutor=? WHERE idAutor=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, autor.getNombreAutor());
            ps.setInt(2, autor.getIdAutor());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM autor WHERE idAutor=?";
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
        String sql = "SELECT COUNT(*) FROM autor";
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
