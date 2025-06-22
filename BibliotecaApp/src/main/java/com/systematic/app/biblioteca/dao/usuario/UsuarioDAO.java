
package com.systematic.app.biblioteca.dao.usuario;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.Usuario;
import java.util.Optional;

/**
 *
 * @author anthony
 */
public interface UsuarioDAO extends CRUD<Usuario>{
    Optional<Usuario> findByNickName(String nickName);
    Optional<Usuario> findByEmail(String email);
}
