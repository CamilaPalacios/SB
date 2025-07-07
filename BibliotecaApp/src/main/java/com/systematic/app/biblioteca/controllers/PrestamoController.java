package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.models.Prestamo;
import com.systematic.app.biblioteca.services.prestamo.PrestamoService;
import com.systematic.app.biblioteca.services.prestamo.PrestamoServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PrestamoController", urlPatterns = {"/prestamos", "/prestamos/*"})
public class PrestamoController extends HttpServlet {

    private PrestamoService prestamoService;

    @Override
    public void init() {
        this.prestamoService = new PrestamoServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            listarPrestamos(request, response);
        } else if (action.equals("/nuevo")) {
            mostrarFormularioNuevo(request, response);
        } else if (action.equals("/editar")) {
            mostrarFormularioEditar(request, response);
        } else if (action.equals("/activos")) {
            listarPrestamosActivos(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            crearPrestamo(request, response);
        } else if (action.equals("/devolver")) {
            registrarDevolucion(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listarPrestamos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("prestamos", prestamoService.listarTodos());
        request.getRequestDispatcher("/views/prestamos.jsp").forward(request, response); 
    }

    private void listarPrestamosActivos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("prestamos", prestamoService.listarActivos());
        request.getRequestDispatcher("/WEB-INF/views/prestamos/activos.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/prestamos/nuevo.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Prestamo prestamo = prestamoService.buscarPorId(Integer.parseInt(id));
        request.setAttribute("prestamo", prestamo);
        request.getRequestDispatcher("/WEB-INF/views/prestamos/editar.jsp").forward(request, response);
    }

    private void crearPrestamo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementar lógica de creación
        response.sendRedirect(request.getContextPath() + "/prestamos");
    }

    private void registrarDevolucion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        prestamoService.registrarDevolucion(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath() + "/prestamos");
    }
}
