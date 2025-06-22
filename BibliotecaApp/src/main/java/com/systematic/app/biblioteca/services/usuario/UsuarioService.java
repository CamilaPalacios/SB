
package com.systematic.app.biblioteca.services.usuario;

import com.systematic.app.biblioteca.models.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anthony
 */

public interface UsuarioService {
    Optional<Usuario> findByNickName(String nickname);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> login(String nickname,String password);
    List<Usuario> findAll();
    int insertarUsuario(Usuario usuario);
    int actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Integer id);
}
