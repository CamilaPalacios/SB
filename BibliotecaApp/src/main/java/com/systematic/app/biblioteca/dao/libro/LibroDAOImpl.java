package com.systematic.app.biblioteca.dao.libro;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.autor.AutorDAO;
import com.systematic.app.biblioteca.dao.autor.AutorDAOImpl;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAOImpl;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAO;
import com.systematic.app.biblioteca.dao.editorial.EditorialDAOImpl;
import com.systematic.app.biblioteca.models.Libro;
import com.systematic.app.biblioteca.models.LibroPopularDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAOImpl implements LibroDAO, AutoCloseable {

    private final Connection connection;

    public LibroDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
        this.connection.setAutoCommit(false);
    }

    public LibroDAOImpl(Connection connection) {
        this.connection = connection;
        try {
            this.connection.setAutoCommit(false); // 💡 Muy importante para commit/rollback manual
        } catch (SQLException e) {
            throw new RuntimeException("Error al configurar autocommit", e);
        }
    }

    @Override
    public Optional<Libro> findById(Integer id) {
        final String SQL = "SELECT * FROM libro WHERE idLibro = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearLibro(rs));
                }
            }
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al buscar libro por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Libro> obtenerTodos() {
        List<Libro> libros = new ArrayList<>();
        final String SQL = "SELECT * FROM libro";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                libros.add(mapearLibro(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al obtener todos los libros", e);
        }
        return libros;
    }

    @Override
    public Integer insertar(Libro libro) {
        final String SQL = "INSERT INTO libro (titulo, anioPublicacion, isbn, idAutor, idCategoria, idEditorial, cantidad) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAnioPublicacion());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getAutor().getIdAutor());
            ps.setInt(5, libro.getCategoria().getIdCategoria());
            ps.setInt(6, libro.getEditorial().getIdEditorial());
            ps.setInt(7, libro.getCantidad());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        connection.commit();
                        return rs.getInt(1);
                    }
                }
            }
            connection.rollback();
            return -1;
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al insertar libro", e);
        }
    }

    @Override
    public int actualizar(Libro libro) {
        final String SQL = "UPDATE libro SET titulo = ?, anioPublicacion = ?, isbn = ?, "
                + "idAutor = ?, idCategoria = ?, idEditorial = ?, cantidad = ? "
                + "WHERE idLibro = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAnioPublicacion());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getAutor().getIdAutor());
            ps.setInt(5, libro.getCategoria().getIdCategoria());
            ps.setInt(6, libro.getEditorial().getIdEditorial());
            ps.setInt(7, libro.getCantidad());
            ps.setInt(8, libro.getIdLibro());

            int result = ps.executeUpdate();
            connection.commit();
            return result;
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al actualizar libro ID: " + libro.getIdLibro(), e);
        }
    }

    @Override
    public int eliminar(Integer id) {
        final String SQL = "DELETE FROM libro WHERE idLibro = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            connection.commit();
            return result;
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al eliminar libro ID: " + id, e);
        }
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();
        final String SQL = "SELECT * FROM libro WHERE titulo LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, "%" + titulo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapearLibro(rs));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al buscar libros por título: " + titulo, e);
        }
        return libros;
    }

    private Libro mapearLibro(ResultSet rs) throws SQLException {
        Libro libro = new Libro();
        libro.setIdLibro(rs.getInt("idLibro"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAnioPublicacion(rs.getInt("anioPublicacion"));
        libro.setIsbn(rs.getString("isbn"));
        libro.setCantidad(rs.getInt("cantidad"));

        // ✅ Instanciar DAOs para obtener objetos completos
        AutorDAO autorDAO = new AutorDAOImpl(connection);
        CategoriaLibroDAO categoriaDAO = new CategoriaLibroDAOImpl(connection);
        EditorialDAO editorialDAO = new EditorialDAOImpl(connection);

        // ✅ Mapear relaciones usando los DAOs
        libro.setAutor(autorDAO.buscarPorId(rs.getInt("idAutor")).orElse(null));
        libro.setCategoria(categoriaDAO.findById(rs.getInt("idCategoria")).orElse(null));
        libro.setEditorial(editorialDAO.findById(rs.getInt("idEditorial")).orElse(null));

        return libro;
    }

    @Override
    public List<LibroPopularDTO> obtenerLibrosMasPrestados(int limit) {
        List<LibroPopularDTO> lista = new ArrayList<>();

        final String SQL = """
        SELECT l.idLibro, l.titulo, a.nombre AS autor, COUNT(p.idPrestamo) AS totalPrestamos
        FROM libro l
        JOIN prestamo p ON l.idLibro = p.idLibro
        JOIN autor a ON l.idAutor = a.idAutor
        GROUP BY l.idLibro, l.titulo, a.nombre
        ORDER BY totalPrestamos DESC
        LIMIT ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LibroPopularDTO dto = new LibroPopularDTO();
                    dto.setId(rs.getInt("idLibro"));
                    dto.setTitulo(rs.getString("titulo"));
                    dto.setAutor(rs.getString("autor"));
                    dto.setTotalPrestamos(rs.getInt("totalPrestamos"));

                    lista.add(dto);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al obtener libros más prestados", e);
        }

        return lista;
    }

    @Override
    public int contar() {
        final String SQL = "SELECT COUNT(*) FROM libro";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("📚 Total de libros en DB: " + count); // Para debug
                return count;
            }
        } catch (SQLException e) {
            rollback();
            e.printStackTrace(); // Para ver el error exacto
            throw new RuntimeException("Error al contar libros", e);
        }
        return 0;
    }

    public int contarDisponibles() {
        final String SQL = "SELECT COUNT(*) FROM libro WHERE cantidad > 0";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al contar libros disponibles", e);
        }
        return 0;
    }

    public int contarPrestados() {
        final String SQL = "SELECT COUNT(DISTINCT idLibro) FROM prestamo WHERE estado = 'ACTIVO'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al contar libros prestados", e);
        }
        return 0;
    }

    private void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al hacer rollback", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> libros = new ArrayList<>();
        final String SQL = "SELECT * FROM libro WHERE cantidad > 0";

        try (PreparedStatement ps = connection.prepareStatement(SQL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                libros.add(mapearLibro(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al obtener libros disponibles", e);
        }

        return libros;
    }

    @Override
    public boolean reducirStock(Integer idLibro) {
        final String SQL = "UPDATE libro SET cantidad = cantidad - 1 WHERE idLibro = ? AND cantidad > 0";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, idLibro);
            int result = ps.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al reducir stock del libro ID: " + idLibro, e);
        }
    }

    @Override
    public boolean aumentarStock(Integer idLibro) {
        final String SQL = "UPDATE libro SET cantidad = cantidad + 1 WHERE idLibro = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, idLibro);
            int result = ps.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al aumentar stock del libro ID: " + idLibro, e);
        }
    }

    @Override
    public int totalRegistros() {
        final String SQL = "SELECT COUNT(*) FROM libro";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Error al contar total de registros de libro", e);
        }
        return 0;
    }

}
