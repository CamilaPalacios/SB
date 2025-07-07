package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.cargo.CargoDAO;
import com.systematic.app.biblioteca.dao.cargo.CargoDAOImpl;
import com.systematic.app.biblioteca.models.Cargo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cargos")
public class ControlCargosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "registrar":
                registrarCargo(req, resp);
                break;
            case "actualizar":
                actualizarCargo(req, resp);
                break;
            case "eliminar":
                eliminarCargo(req, resp);
                break;
            default:
                listarCargos(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarCargos(req, resp);
    }

    // ========== LISTAR ==========
    private void listarCargos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO dao = new CargoDAOImpl(connection);
            List<Cargo> cargos = dao.obtenerTodos();
            req.setAttribute("cargos", cargos);
            req.getRequestDispatcher("/views/cargos.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensajeError", "Error al listar cargos: " + e.getMessage());
            req.getRequestDispatcher("/views/cargos.jsp").forward(req, resp);
        }
    }

    // ========== REGISTRAR ==========
    private void registrarCargo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO dao = new CargoDAOImpl(connection);
            Cargo cargo = new Cargo();
            cargo.setNombreCargo(req.getParameter("nombreCargo").trim());

            dao.insertar(cargo);
            resp.sendRedirect(req.getContextPath() + "/cargos?registro=exito");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/cargos?mensajeError=" + e.getMessage());
        }
    }

    // ========== ACTUALIZAR ==========
    private void actualizarCargo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO dao = new CargoDAOImpl(connection);
            Cargo cargo = new Cargo();
            cargo.setIdCargo(Integer.parseInt(req.getParameter("idCargo")));
            cargo.setNombreCargo(req.getParameter("nombreCargo").trim());

            dao.actualizar(cargo);
            resp.sendRedirect(req.getContextPath() + "/cargos?actualizado=exito");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/cargos?mensajeError=" + e.getMessage());
        }
    }

    // ========== ELIMINAR ==========
    private void eliminarCargo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CargoDAO dao = new CargoDAOImpl(connection);
            int id = Integer.parseInt(req.getParameter("idCargo"));

            if (dao.tieneUsuariosAsociados(id)) {
                resp.sendRedirect(req.getContextPath() + "/cargos?mensajeError=Cargo tiene usuarios asociados");
            } else {
                dao.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/cargos?eliminado=exito");
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/cargos?mensajeError=" + e.getMessage());
        }
    }
}

