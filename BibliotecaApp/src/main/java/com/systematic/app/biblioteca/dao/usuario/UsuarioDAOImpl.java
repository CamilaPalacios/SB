package com.systematic.app.biblioteca.dao.usuario;

import com.systematic.app.biblioteca.models.Cargo;
import com.systematic.app.biblioteca.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Usuario> findByNickName(String nickname) {
        String sql = "SELECT u.*, c.idCargo, c.nombreCargo FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.nickname = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nickname);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUsuario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por nickname", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT u.*, c.idCargo, c.nombreCargo FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUsuario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por email", e);
        }
        return Optional.empty();
    }

    @Override
    public void guardarTokenRecuperacion(String email, String token, Timestamp expiracion) {
        String sql = "UPDATE usuario SET tokenRecuperacion = ?, expiracionToken = ? WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);
            ps.setTimestamp(2, expiracion);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar token de recuperación", e);
        }
    }

    @Override
    public Optional<Usuario> findByTokenRecuperacion(String token) {
        String sql = "SELECT * FROM usuario WHERE tokenRecuperacion = ? AND expiracionToken > CURRENT_TIMESTAMP";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNickName(rs.getString("nickName")); // ✅
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidoPaterno(rs.getString("apellidoPaterno"));
                    usuario.setApellidoMaterno(rs.getString("apellidoMaterno"));
                    usuario.setTelefonoCelular(rs.getString("telefonoCelular"));
                    usuario.setEducacion(rs.getString("educacion"));
                    usuario.setDireccion(rs.getString("direccion"));

                    // ✅ Token y expiración
                    usuario.setTokenRecuperacion(rs.getString("token_recuperacion"));
                    usuario.setTokenExpiracion(rs.getTimestamp("token_expiracion").toLocalDateTime());

                    // ✅ Cargo si tienes una relación con tabla "cargo"
                    // Si tu tabla tiene idCargo, necesitarás cargar el objeto Cargo
                    Cargo cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    usuario.setCargo(cargo);

                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por token de recuperación", e);
        }

        return Optional.empty();
    }

    @Override
    public void actualizarPasswordYEliminarToken(int idUsuario, String nuevaPassword) {
        String sql = "UPDATE usuario SET password = ?, tokenRecuperacion = NULL, expiracionToken = NULL WHERE idUsuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuevaPassword);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar contraseña", e);
        }
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT u.*, c.idCargo, c.nombreCargo FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.idUsuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUsuario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.*, c.idCargo, c.nombreCargo FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapUsuario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los usuarios", e);
        }
        return lista;
    }

    @Override
    public int insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre,apellidoPaterno,apellidoMaterno,telefonoCelular,email,nickname,password,direccion,educacion,idCargo) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoPaterno());
            ps.setString(3, usuario.getApellidoMaterno());
            ps.setString(4, usuario.getTelefonoCelular());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNickName());
            ps.setString(7, usuario.getPassword());
            ps.setString(8, usuario.getDireccion());
            ps.setString(9, usuario.getEducacion());
            ps.setInt(10, usuario.getCargo().getIdCargo());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return affectedRows;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }

    @Override
    public int actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefonoCelular = ?, email = ?, nickname = ?, password = ?, direccion = ?, educacion = ?, idCargo = ? WHERE idUsuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoPaterno());
            ps.setString(3, usuario.getApellidoMaterno());
            ps.setString(4, usuario.getTelefonoCelular());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNickName());
            ps.setString(7, usuario.getPassword());
            ps.setString(8, usuario.getDireccion());
            ps.setString(9, usuario.getEducacion());
            ps.setInt(10, usuario.getCargo().getIdCargo());
            ps.setInt(11, usuario.getIdUsuario());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) FROM usuario";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar usuarios", e);
        }
        return 0;
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Cargo cargo = new Cargo();
        cargo.setIdCargo(rs.getInt("idCargo"));
        cargo.setNombreCargo(rs.getString("nombreCargo"));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidoPaterno(rs.getString("apellidoPaterno"));
        usuario.setApellidoMaterno(rs.getString("apellidoMaterno"));
        usuario.setTelefonoCelular(rs.getString("telefonoCelular"));
        usuario.setEmail(rs.getString("email"));
        usuario.setNickName(rs.getString("nickname"));
        usuario.setPassword(rs.getString("password"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setEducacion(rs.getString("educacion"));
        usuario.setCargo(cargo);
        return usuario;
    }
}
