package com.systematic.app.biblioteca.dao.usuario;

import com.systematic.app.biblioteca.models.Cargo;
import com.systematic.app.biblioteca.models.Usuario;
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

public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Usuario> findByNickName(String nickName) {
        String sql = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, "
                + "u.telefonoCelular, u.email, u.nickname, u.password, "
                + "c.idCargo, c.nombreCargo "
                + "FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.nickname = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nickName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                usuario.setCargo(cargo);

                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, "
                + "u.telefonoCelular, u.email, u.nickname, u.password, "
                + "c.idCargo, c.nombreCargo "
                + "FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                usuario.setCargo(cargo);

                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, "
                + "u.telefonoCelular, u.email, u.nickname, u.password, "
                + "c.idCargo, c.nombreCargo "
                + "FROM usuario u INNER JOIN cargo c ON c.idCargo = u.idCargo WHERE u.idUsuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                usuario.setCargo(cargo);

                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return Optional.empty();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "select u.idUsuario,u.nombre,u.apellidoPaterno,u.apellidoMaterno,"
                + "u.telefonoCelular,u.email,u.nickname,u.password,c.idCargo,c.nombreCargo"
                + "from usuario u inner join cargo c on c.idCargo = u.idCargo";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNombreCargo(rs.getString("nombreCargo"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidoPaterno("apellidoPaterno");
                usuario.setApellidoMaterno("apellidoMaterno");
                usuario.setTelefonoCelular("telefonoCelular");
                usuario.setEmail("email");
                usuario.setNickName("nickname");
                usuario.setPassword("password");
                usuario.setCargo(cargo);

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return listaUsuarios;
    }

    @Override
    public int insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre,apellidoPaterno,apellidoMaterno,telefonoCelular,email,nickname,password,idCargo)"
                + " values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoPaterno());
            ps.setString(3, usuario.getApellidoMaterno());
            ps.setString(4, usuario.getTelefonoCelular());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNickName());
            ps.setString(7, usuario.getPassword());
            ps.setInt(8, usuario.getCargo().getIdCargo());

            return ps.executeUpdate();
  
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefonoCelular = ?, "
                + "email = ?, nickname = ?, password = ?, idCargo = ? WHERE idUsuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoPaterno());
            ps.setString(3, usuario.getApellidoMaterno());
            ps.setString(4, usuario.getTelefonoCelular());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNickName());
            ps.setString(7, usuario.getPassword());
            ps.setInt(8, usuario.getCargo().getIdCargo());
            ps.setInt(9, usuario.getIdUsuario());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int totalRegistros() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM usuario";
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
