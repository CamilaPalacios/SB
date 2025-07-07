package com.systematic.app.biblioteca.dao.usuario;

import com.systematic.app.biblioteca.models.Usuario;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {

    // Inserta un usuario nuevo y devuelve su ID generado
    int insertar(Usuario usuario);

    // Actualiza un usuario existente
    int actualizar(Usuario usuario);

    // Elimina un usuario por ID
    int eliminar(Integer id);

    // Busca un usuario por ID
    Optional<Usuario> findById(Integer id);

    // Lista todos los usuarios
    List<Usuario> obtenerTodos();

    // Busca un usuario por su email
    Optional<Usuario> findByEmail(String email);

    // Busca un usuario por su nickname
    Optional<Usuario> findByNickName(String nickname);

    // Guarda el token de recuperación de contraseña
    void guardarTokenRecuperacion(String email, String token, Timestamp expiracion);

    // Busca un usuario por su token de recuperación válido
    Optional<Usuario> findByTokenRecuperacion(String token);

    // Actualiza la contraseña y limpia el token
    void actualizarPasswordYEliminarToken(int idUsuario, String nuevaPassword);

    // Retorna la cantidad total de usuarios
    int contar();
}
