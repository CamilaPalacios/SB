package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/actualizar-perfil")
public class ActualizarPerfilServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Leer campos del formulario
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
        usuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
        usuario.setTelefonoCelular(request.getParameter("telefonoCelular"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setNickName(request.getParameter("nickName"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setEducacion(request.getParameter("educacion"));

        int idCargo = Integer.parseInt(request.getParameter("idCargo"));
        if (usuario.getCargo() != null) {
            usuario.getCargo().setIdCargo(idCargo);
        }

        // Guardar cambios en BD
        try (Connection conn = DBConnection.getConnection()) {
            UsuarioDAO dao = new UsuarioDAOImpl(conn);
            dao.actualizar(usuario);
            session.setAttribute("usuarioLogueado", usuario);
            response.sendRedirect(request.getContextPath() + "/views/profile.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error actualizando perfil");
        }
    }
}
