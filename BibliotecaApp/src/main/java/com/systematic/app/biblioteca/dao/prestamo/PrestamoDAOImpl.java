package com.systematic.app.biblioteca.dao.prestamo;

import com.systematic.app.biblioteca.models.Prestamo;
import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.models.PrestamosPorMesDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {

    private final Connection connection;

    // ✅ Constructor que recibe conexión externa
    public PrestamoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Prestamo> listarTodos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                prestamos.add(mapearPrestamo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar préstamos", e);
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> listarActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE estado = 'ACTIVO'";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                prestamos.add(mapearPrestamo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar préstamos activos", e);
        }
        return prestamos;
    }

    @Override
    public Prestamo buscarPorId(Integer id) {
        String sql = "SELECT * FROM prestamos WHERE id_prestamo = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPrestamo(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar préstamo por ID", e);
        }
        return null;
    }

    @Override
    public Prestamo crear(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion_estimada, estado) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucionEstimada()));
            stmt.setString(5, "ACTIVO");

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear préstamo, ninguna fila afectada");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    prestamo.setIdPrestamo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al obtener ID generado");
                }
            }

            return prestamo;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear préstamo", e);
        }
    }

    @Override
    public List<Prestamo> obtenerPrestamosRecientes(int limit) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos ORDER BY fecha_prestamo DESC LIMIT ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prestamos.add(mapearPrestamo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener préstamos recientes", e);
        }

        return prestamos;
    }

    @Override
    public Prestamo actualizar(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET id_usuario = ?, id_libro = ?, fecha_prestamo = ?, "
                + "fecha_devolucion = ?, fecha_devolucion_estimada = ?, estado = ? "
                + "WHERE id_prestamo = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(4, prestamo.getFechaDevolucion() != null
                    ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            stmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucionEstimada()));
            stmt.setString(6, prestamo.getEstado());
            stmt.setInt(7, prestamo.getIdPrestamo());

            stmt.executeUpdate();
            return prestamo;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar préstamo", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM prestamos WHERE id_prestamo = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar préstamo", e);
        }
    }

    @Override
    public void registrarDevolucion(Integer idPrestamo) {
        String sql = "UPDATE prestamos SET fecha_devolucion = CURRENT_DATE, estado = 'DEVUELTO' "
                + "WHERE id_prestamo = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar devolución", e);
        }
    }

    @Override
    public List<Prestamo> buscarPorUsuario(Integer idUsuario) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE id_usuario = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prestamos.add(mapearPrestamo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar préstamos por usuario", e);
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> buscarPorLibro(Integer idLibro) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE id_libro = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prestamos.add(mapearPrestamo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar préstamos por libro", e);
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> listarVencidos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE estado = 'ACTIVO' AND fecha_devolucion_estimada < CURRENT_DATE";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                prestamos.add(mapearPrestamo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar préstamos vencidos", e);
        }
        return prestamos;
    }

    @Override
    public int contarPrestamosActivos() {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE estado = 'ACTIVO'";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar préstamos activos", e);
        }
        return 0;
    }

    @Override
    public List<PrestamosPorMesDTO> contarPrestamosPorMes() {
        List<PrestamosPorMesDTO> resultados = new ArrayList<>();
        String sql = "SELECT MONTH(fecha_prestamo) AS mes, COUNT(*) AS total "
                + "FROM prestamos "
                + "GROUP BY MONTH(fecha_prestamo) "
                + "ORDER BY mes";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int mes = rs.getInt("mes");
                int total = rs.getInt("total");
                resultados.add(new PrestamosPorMesDTO(mes, total));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar préstamos por mes", e);
        }

        return resultados;
    }

    @Override
    public List<Prestamo> obtenerProximosAVencer(int dias) {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos "
                + "WHERE estado = 'ACTIVO' "
                + "AND fecha_devolucion_estimada BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL ? DAY)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, dias);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPrestamo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener préstamos próximos a vencer", e);
        }

        return lista;
    }

    @Override
    public int contarPrestamosVencidos() {
        String sql = "SELECT COUNT(*) FROM prestamo WHERE fechaDevolucion < CURRENT_DATE AND estado = 'ACTIVO'";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar préstamos vencidos", e);
        }
        return 0;
    }

    private Prestamo mapearPrestamo(ResultSet rs) throws SQLException {
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
        prestamo.setIdUsuario(rs.getInt("id_usuario"));
        prestamo.setIdLibro(rs.getInt("id_libro"));
        prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo").toLocalDate());
        prestamo.setFechaDevolucion(rs.getDate("fecha_devolucion") != null
                ? rs.getDate("fecha_devolucion").toLocalDate() : null);
        prestamo.setFechaDevolucionEstimada(rs.getDate("fecha_devolucion_estimada").toLocalDate());
        prestamo.setEstado(rs.getString("estado"));
        return prestamo;
    }
}
