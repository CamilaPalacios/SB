package com.systematic.app.biblioteca.controllers;

import com.google.gson.Gson;
import com.systematic.app.biblioteca.models.EstadisticasDTO;
import com.systematic.app.biblioteca.services.dashboard.DashboardService;
import com.systematic.app.biblioteca.services.dashboard.DashboardServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "DashboardServlet", urlPatterns = {
    "/dashboard",
    "/dashboard/stats",
    "/dashboard/prestamos-proximos"
})
public class DashboardServlet extends HttpServlet {

    private DashboardService dashboardService;
    private Gson gson;

    @Override
    public void init() {
        this.dashboardService = new DashboardServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/dashboard/stats".equals(path)) {
            enviarEstadisticas(response);
        } else if ("/dashboard/prestamos-proximos".equals(path)) {
            enviarPrestamosProximos(response);
        } else {
            mostrarDashboard(request, response);
        }
    }

    private void enviarEstadisticas(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> stats = Map.of(
                "totalLibros", dashboardService.contarLibros(),
                "totalUsuarios", dashboardService.contarUsuarios(),
                "totalPrestamosActivos", dashboardService.contarPrestamosActivos(),
                "prestamosPorMes", dashboardService.obtenerPrestamosPorMes(),
                "librosPopulares", dashboardService.obtenerLibrosMasPrestados(5)
        );

        response.getWriter().write(gson.toJson(stats));
    }

    private void enviarPrestamosProximos(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(dashboardService.obtenerPrestamosProximosAVencer(3)));
    }

    private void mostrarDashboard(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    EstadisticasDTO stats = dashboardService.obtenerEstadisticasIniciales();
    System.out.println("DEBUG Estadísticas: " + stats); // 👈 agrega esto

    request.setAttribute("estadisticas", stats);
    request.setAttribute("prestamosRecientes", dashboardService.obtenerPrestamosRecientes());
    request.setAttribute("librosPopulares", dashboardService.obtenerLibrosMasPrestados(5));

    request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

}

}
