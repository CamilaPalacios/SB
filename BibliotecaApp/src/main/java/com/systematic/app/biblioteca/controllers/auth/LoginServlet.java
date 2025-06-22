package com.systematic.app.biblioteca.controllers.auth;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.services.usuario.UsuarioService;
import com.systematic.app.biblioteca.services.usuario.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 *
 * @author anthony
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("txtNickName");
        String password = req.getParameter("txtPassword");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter out = resp.getWriter(); Connection connection = DBConnection.getConnection()) {

            //Pasamos la conexión a UsuarioDAOImpl
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);

            //Pasamos la conexión a UsuarioServiceImpl
            UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);

            //Método Login
            usuarioService.login(nickname, password)
                    .ifPresentOrElse(usuario -> {
                        //Login exitoso
                        HttpSession session = req.getSession();
                        session.setAttribute("usuarioLogueado", usuario);
                        try {
                            out.print("{\"success\": true}");
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    }, () -> {
                        //Login fallido
                        try {
                            out.print("{\"success\": false}");
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    });

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"success\": false, \"error\": \"Error en el servidor\"}");
        }

    }

}
