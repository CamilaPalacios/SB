package com.systematic.app.biblioteca.services.usuario;

import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.models.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;


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
    public List<Usuario> findAll() {
        return usuarioDAO.obtenerTodos();
    }

    @Override
    public int insertarUsuario(Usuario usuario) {
        if (usuario.getCargo() == null || usuario.getCargo().getIdCargo()== null) {
            throw new IllegalArgumentException("El usuario debe tener un cargo válido.");
        }
        return usuarioDAO.insertar(usuario);
    }

    @Override
    public int actualizarUsuario(Usuario usuario) {
        if (usuario.getCargo() == null || usuario.getCargo().getIdCargo()== null) {
            throw new IllegalArgumentException("El usuario debe tener un cargo válido.");
        }
        return usuarioDAO.actualizar(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioDAO.eliminar(id);
    }

    @Override
    public Optional<Usuario> login(String nickname, String password) {
        Optional<Usuario> userOpt = usuarioDAO.findByNickName(nickname);

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}
