package com.systematic.app.biblioteca.controllers.auth;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.services.usuario.UsuarioService;
import com.systematic.app.biblioteca.services.usuario.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("txtNickName").trim();
        String password = req.getParameter("txtPassword").trim();
        if (nickname == null || nickname.isBlank() || password == null || password.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print("{\"success\": false, \"error\": \"Todos los campos son obligatorios\"}");
            return;
        }

        System.out.println("NICKNAME INGRESADO: [" + nickname + "]");
        System.out.println("PASSWORD INGRESADO: [" + password + "]");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        try (Connection connection = DBConnection.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection); // ✅ CORRECTO
            UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);

            usuarioService.login(nickname, password)
                    .ifPresentOrElse(usuario -> {
                        HttpSession session = req.getSession();
                        session.setAttribute("usuarioLogueado", usuario);
                        System.out.println("✅ LOGIN CORRECTO PARA: " + nickname);

                        // ✅ Recuérdame (guardar o eliminar cookie)
                        String remember = req.getParameter("remember");
                        if ("on".equals(remember)) {
                            Cookie cookie = new Cookie("recordarUsuario", nickname);
                            cookie.setMaxAge(60 * 60 * 24 * 7); // 7 días
                            resp.addCookie(cookie);
                        } else {
                            Cookie cookie = new Cookie("recordarUsuario", "");
                            cookie.setMaxAge(0);
                            resp.addCookie(cookie);
                        }

                        out.print("{\"success\": true}");
                    }, () -> {
                        System.out.println("❌ LOGIN FALLIDO PARA: " + nickname);
                        try {
                            out.print("{\"success\": false}");
                        } catch (Exception e) {
                            e.printStackTrace();
                            out.println("Error: " + e.getMessage());
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"success\": false, \"error\": \"Error en el servidor\"}");
        }
    }
}
