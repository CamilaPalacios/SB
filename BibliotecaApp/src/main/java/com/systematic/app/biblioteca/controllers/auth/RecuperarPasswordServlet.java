package com.systematic.app.biblioteca.controllers.auth;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.models.Usuario;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/recuperar-password")
public class RecuperarPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String token = req.getParameter("token");
        String nuevaPassword = req.getParameter("nuevaPassword");
        String confirmarPassword = req.getParameter("confirmarPassword");

        if (nuevaPassword == null || confirmarPassword == null
                || nuevaPassword.trim().isEmpty() || confirmarPassword.trim().isEmpty()
                || !nuevaPassword.equals(confirmarPassword)) {

            resp.sendRedirect("views/cambiar-password.jsp?token=" + token + "&error=1");
            return;
        }

        UsuarioDAOImpl usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAOImpl(DBConnection.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(RecuperarPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Optional<Usuario> usuarioOpt = usuarioDAO.findByTokenRecuperacion(token);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuarioDAO.actualizarPasswordYEliminarToken(usuario.getIdUsuario(), nuevaPassword);
            resp.sendRedirect("login.jsp?recuperado=1");
        } else {
            resp.sendRedirect("views/cambiar-password.jsp?token=" + token + "&error=2");
        }
    }
}
