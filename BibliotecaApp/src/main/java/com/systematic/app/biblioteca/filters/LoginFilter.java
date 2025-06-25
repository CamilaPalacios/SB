package com.systematic.app.biblioteca.filters;

import com.systematic.app.biblioteca.models.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author anthony
 */

@WebFilter("/views/*")
public class LoginFilter implements Filter {
    
    private static final List<String> rutasPermitidas = Arrays.asList(
            "/views/forgotPassword.jsp",
            "/views/registerUser.jsp"
    );
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String path = request.getRequestURI().substring(request.getContextPath().length());
        
        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        
        boolean esPermitido = rutasPermitidas.stream().anyMatch(path::equals);
        
        if (usuario != null || esPermitido) {
            fc.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        
    }
    
    @Override
    public void destroy() {
    }
    
}
