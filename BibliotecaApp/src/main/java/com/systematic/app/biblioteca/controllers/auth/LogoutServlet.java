
package com.systematic.app.biblioteca.controllers.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author anthony
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Invalidar la sesión para cerrar sesión
        req.getSession().invalidate();
        //Redirigir al login
        resp.sendRedirect(req.getContextPath()+"/login.jsp?logout=success");
    }
    
    
}