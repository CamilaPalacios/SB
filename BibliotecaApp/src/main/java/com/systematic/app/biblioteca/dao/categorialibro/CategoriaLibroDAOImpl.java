package com.systematic.app.biblioteca.dao.categorialibro;

import com.systematic.app.biblioteca.models.CategoriaLibro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaLibroDAOImpl implements CategoriaLibroDAO {

    private final Connection connection;

    public CategoriaLibroDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CategoriaLibro> listarTodos() {
        List<CategoriaLibro> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_libro";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaLibro categoria = new CategoriaLibro();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar categorías", e);
        }

        return categorias;
    }

    @Override
    public CategoriaLibro obtenerPorId(int id) {
        String sql = "SELECT * FROM categoria_libro WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaLibro categoria = new CategoriaLibro();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener categoría por ID", e);
        }

        return null;
    }

    @Override
    public boolean insertar(CategoriaLibro categoria) {
        String sql = "INSERT INTO categoria_libro (nombreCategoria) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar categoría", e);
        }
    }

    @Override
    public boolean actualizar(CategoriaLibro categoria) {
        String sql = "UPDATE categoria_libro SET nombreCategoria = ? WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar categoría", e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM categoria_libro WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar categoría", e);
        }
    }

    @Override
    public boolean existeCategoria(String nombre) {
        String sql = "SELECT COUNT(*) FROM categoria_libro WHERE nombreCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al verificar existencia de categoría", e);
        }

        return false;
    }

    @Override
    public boolean tieneLibrosAsociados(int id) {
        String sql = "SELECT COUNT(*) FROM libro WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al verificar si la categoría tiene libros asociados", e);
        }

        return false;
    }

    @Override
    public List<CategoriaLibro> buscarPorNombre(String nombre) {
        List<CategoriaLibro> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_libro WHERE nombreCategoria LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriaLibro categoria = new CategoriaLibro();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar categorías por nombre", e);
        }

        return categorias;
    }

    // ✅ MÉTODO AGREGADO: findById
    @Override
    public Optional<CategoriaLibro> findById(int id) {
        String sql = "SELECT * FROM categoria_libro WHERE idCategoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaLibro categoria = new CategoriaLibro();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                    return Optional.of(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar categoría por ID", e);
        }

        return Optional.empty();
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) FROM categorias_libros";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar categorías", e);
        }
        return 0;
    }

}
