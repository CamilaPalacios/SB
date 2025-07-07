package com.systematic.app.biblioteca.dao.autor;

import com.systematic.app.biblioteca.models.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAOImpl implements AutorDAO {

    private final Connection connection;

    public AutorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Autor crearAutor(Autor autor) {
        String sql = "INSERT INTO autor(nombreAutor, apellidoPaterno, apellidoMaterno) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, autor.getNombreAutor());
            ps.setString(2, autor.getApellidoPaterno());
            ps.setString(3, autor.getApellidoMaterno());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new RuntimeException("No se pudo insertar el autor");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    autor.setIdAutor(rs.getInt(1));
                    return autor;
                } else {
                    throw new RuntimeException("No se obtuvo el ID del autor insertado");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear autor", e);
        }
    }

    @Override
    public Optional<Autor> buscarPorId(Integer id) {
        String sql = "SELECT * FROM autor WHERE idAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapAutor(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar autor por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Autor> listarTodos() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor ORDER BY apellidoPaterno, apellidoMaterno, nombreAutor";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                autores.add(mapAutor(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar autores", e);
        }
        return autores;
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        String sql = "UPDATE autor SET nombreAutor = ?, apellidoPaterno = ?, apellidoMaterno = ? WHERE idAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, autor.getNombreAutor());
            ps.setString(2, autor.getApellidoPaterno());
            ps.setString(3, autor.getApellidoMaterno());
            ps.setInt(4, autor.getIdAutor());
            int updated = ps.executeUpdate();
            if (updated > 0) {
                return autor;
            } else {
                throw new RuntimeException("No se encontró el autor para actualizar");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar autor", e);
        }
    }

    @Override
    public void eliminarAutor(Integer id) {
        if (tieneLibrosAsociados(id)) {
            throw new RuntimeException("No se puede eliminar el autor porque tiene libros asociados");
        }

        String sql = "DELETE FROM autor WHERE idAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) {
                throw new RuntimeException("No se encontró el autor para eliminar");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar autor", e);
        }
    }

    @Override
    public List<Autor> buscarPorNombre(String nombre) {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor WHERE CONCAT(nombreAutor, ' ', apellidoPaterno, ' ', apellidoMaterno) LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    autores.add(mapAutor(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar autores por nombre", e);
        }
        return autores;
    }

    private boolean tieneLibrosAsociados(Integer idAutor) {
        String sql = "SELECT COUNT(*) FROM libro WHERE idAutor = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idAutor);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar libros asociados", e);
        }
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) FROM autores";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar autores", e);
        }
        return 0;
    }

    private Autor mapAutor(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setIdAutor(rs.getInt("idAutor"));
        autor.setNombreAutor(rs.getString("nombreAutor"));
        autor.setApellidoPaterno(rs.getString("apellidoPaterno"));
        autor.setApellidoMaterno(rs.getString("apellidoMaterno"));
        return autor;
    }
}
