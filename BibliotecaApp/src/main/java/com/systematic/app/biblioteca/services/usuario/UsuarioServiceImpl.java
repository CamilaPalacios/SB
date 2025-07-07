package com.systematic.app.biblioteca.services.usuario;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.models.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    // ✅ Constructor por defecto
    public UsuarioServiceImpl() {
        try {
            Connection conn = DBConnection.getConnection();
            this.usuarioDAO = new UsuarioDAOImpl(conn);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar UsuarioServiceImpl", e);
        }
    }

    // ✅ Constructor con DAO externo
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Optional<Usuario> findByNickName(String nickname) {
        return usuarioDAO.findByNickName(nickname);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public Optional<Usuario> login(String nickname, String password) {
        Optional<Usuario> usuarioOpt = usuarioDAO.findByNickName(nickname);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) { // Puedes mejorar usando hash
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.obtenerTodos();
    }

    @Override
    public int insertarUsuario(Usuario usuario) {
        return usuarioDAO.insertar(usuario);
    }

    @Override
    public int actualizarUsuario(Usuario usuario) {
        int filas = usuarioDAO.actualizar(usuario);
        if (filas == 0) {
            throw new RuntimeException("No se actualizó el usuario con ID: " + usuario.getIdUsuario());
        }
        return filas;
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioDAO.eliminar(id);
    }
}
