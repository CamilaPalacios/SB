package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.cargo.CargoDAO;
import com.systematic.app.biblioteca.dao.cargo.CargoDAOImpl;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.models.Cargo;
import com.systematic.app.biblioteca.models.Usuario;
import com.systematic.app.biblioteca.services.usuario.UsuarioService;
import com.systematic.app.biblioteca.services.usuario.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuarios")
public class ControlUsuariosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "registrar":
                try (Connection connection = DBConnection.getConnection()) {
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
                    UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);

                    Usuario usuario = new Usuario();
                    usuario.setNombre(req.getParameter("txtNombre"));
                    usuario.setApellidoPaterno(req.getParameter("txtApellidoPaterno"));
                    usuario.setApellidoMaterno(req.getParameter("txtApellidoMaterno"));
                    usuario.setTelefonoCelular(req.getParameter("txtCelular"));
                    usuario.setEmail(req.getParameter("txtEmail"));
                    usuario.setNickName(req.getParameter("txtNickName"));
                    usuario.setDireccion(req.getParameter("txtDireccion"));
                    usuario.setEducacion(req.getParameter("txtEducacion"));

                    String password = req.getParameter("txtPassword");
                    String repetirPassword = req.getParameter("txtRepetirPassword");
                    Integer idCargo = Integer.valueOf(req.getParameter("idCargo"));

                    Cargo cargo = new Cargo();
                    cargo.setIdCargo(idCargo);
                    usuario.setCargo(cargo);

                    if (password.equals(repetirPassword)) {
                        usuario.setPassword(password);
                        int resultado = usuarioService.insertarUsuario(usuario);
                        if (resultado == 1) {
                            // ✅ Registro exitoso: redirige al login con mensaje
                            resp.sendRedirect(req.getContextPath() + "/login.jsp?registro=exito");
                            return;
                        } else {
                            req.setAttribute("errorCreacion", "No se pudo registrar el usuario");
                        }
                    } else {
                        req.setAttribute("errorPassword", "Las contraseñas no coinciden");
                    }

                    cargarCargos(req);
                    req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);

                } catch (Exception e) {
                    req.setAttribute("errorCreacion", "Error interno: " + e.getMessage());
                    cargarCargos(req);
                    req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);
                }
                break;

            case "listar":
            default:
                listarUsuarios(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarUsuarios(req, resp);
    }

    private void listarUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            req.setAttribute("usuarios", usuarios);
            req.getRequestDispatcher("/views/usuarios.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Error al listar usuarios: " + e.getMessage());
            req.getRequestDispatcher("/views/usuarios.jsp").forward(req, resp);
        }
    }

    private void cargarCargos(HttpServletRequest req) {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO cargoDAO = new CargoDAOImpl(connection);
            List<Cargo> cargos = cargoDAO.obtenerTodos();
            req.setAttribute("cargos", cargos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
