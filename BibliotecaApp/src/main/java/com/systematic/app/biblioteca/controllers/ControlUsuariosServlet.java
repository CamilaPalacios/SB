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

/**
 *
 * @author anthony
 */
@WebServlet("/usuarios")
public class ControlUsuariosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        System.out.println("accion => " + accion);
        if (accion == null) {
            accion = "index.jsp";
        }
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
                    String password = req.getParameter("txtPassword");
                    String repetirPassword = req.getParameter("txtRepetirPassword");
                    Integer idCargo = Integer.parseInt(req.getParameter("idCargo"));
                    
                    Cargo cargo = new Cargo();
                    cargo.setIdCargo(idCargo);                 
                    usuario.setCargo(cargo);
                    
                    if (password.equals(repetirPassword)) {
                        usuario.setPassword(password);
                        int resultado = usuarioService.insertarUsuario(usuario);
                        System.out.println("resultado => "+resultado);
                        if (resultado == 1) {
                            req.setAttribute("usuarioCreado", true);
                            req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("errorCreacion", "No se pudo registrar el usuario");
                            req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);
                        }
                    } else {
                        req.setAttribute("errorPassword", "Las Contraseñas no coinciden");
                        req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);
                    }

                } catch (Exception e) {
                    req.setAttribute("errorCreacion", e.getMessage());
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO cargoDAO = new CargoDAOImpl(connection);
            List<Cargo> cargos = cargoDAO.obtenerTodos();
            System.out.println("cargos => "+cargos);
            req.setAttribute("cargos", cargos);
            req.getRequestDispatcher("/views/registerUser.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar cargos.");
        }
    }

}
