package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.autor.AutorDAOImpl;
import com.systematic.app.biblioteca.models.Autor;
import com.systematic.app.biblioteca.services.autor.AutorService;
import com.systematic.app.biblioteca.services.autor.AutorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AutorServlet", urlPatterns = {"/autores"})
public class AutorServlet extends HttpServlet {

    private AutorService autorService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            AutorDAOImpl autorDAO = new AutorDAOImpl(connection);
            autorService = new AutorServiceImpl(autorDAO);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar el servicio de autores", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "list";
            }

            switch (action) {
                case "new":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "edit":
                    mostrarFormularioEdicion(request, response);
                    break;
                case "delete":
                    mostrarConfirmacionEliminacion(request, response);
                    break;
                case "list":
                default:
                    listarAutores(request, response);
            }
        } catch (SQLException e) {
            manejarError(request, response, "Error al procesar la solicitud: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                crearAutor(request, response);
            } else if ("update".equals(action)) {
                actualizarAutor(request, response);
            } else if ("confirm-delete".equals(action)) {
                eliminarAutor(request, response);
            } else {
                listarAutores(request, response);
            }
        } catch (SQLException e) {
            manejarError(request, response, "Error al procesar la solicitud: " + e.getMessage(), e);
        }
    }

    private void listarAutores(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Autor> autores = autorService.listarTodos();
        request.setAttribute("autores", autores);
        request.getRequestDispatcher("/pages/autores/list.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("accion", "create");
        request.getRequestDispatcher("/pages/autores/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Optional<Autor> autor = autorService.buscarPorId(id);

        if (autor.isPresent()) {
            request.setAttribute("autor", autor.get());
            request.setAttribute("accion", "update");
            request.getRequestDispatcher("/views/autores/form.jsp").forward(request, response);
        } else {
            manejarError(request, response, "Autor no encontrado con ID: " + id, null);
        }
    }

    private void mostrarConfirmacionEliminacion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Optional<Autor> autor = autorService.buscarPorId(id);

        if (autor.isPresent()) {
            request.setAttribute("autor", autor.get());
            request.getRequestDispatcher("/views/autores/confirm-delete.jsp").forward(request, response);
        } else {
            manejarError(request, response, "Autor no encontrado con ID: " + id, null);
        }
    }

    private void crearAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            Autor autor = new Autor();
            autor.setNombreAutor(request.getParameter("nombre"));
            autor.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            autor.setApellidoMaterno(request.getParameter("apellidoMaterno"));

            autorService.crearAutor(autor);
            connection.commit();

            request.getSession().setAttribute("mensajeExito", "Autor creado exitosamente");
            response.sendRedirect(request.getContextPath() + "/autores");

        } catch (SQLException e) {
            DBConnection.rollback(connection);
            manejarError(request, response, "Error al crear el autor: " + e.getMessage(), e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    private void actualizarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            Autor autor = new Autor();
            autor.setIdAutor(Integer.parseInt(request.getParameter("id")));
            autor.setNombreAutor(request.getParameter("nombre"));
            autor.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            autor.setApellidoMaterno(request.getParameter("apellidoMaterno"));

            autorService.actualizarAutor(autor);
            connection.commit();

            request.getSession().setAttribute("mensajeExito", "Autor actualizado exitosamente");
            response.sendRedirect(request.getContextPath() + "/autores");

        } catch (SQLException e) {
            DBConnection.rollback(connection);
            manejarError(request, response, "Error al actualizar el autor: " + e.getMessage(), e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    private void eliminarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            Integer id = Integer.parseInt(request.getParameter("id"));
            autorService.eliminarAutor(id);
            connection.commit();

            request.getSession().setAttribute("mensajeExito", "Autor eliminado exitosamente");
            response.sendRedirect(request.getContextPath() + "/autores");

        } catch (SQLException e) {
            DBConnection.rollback(connection);
            manejarError(request, response, "Error al eliminar el autor: " + e.getMessage(), e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response,
            String mensaje, Exception e) throws ServletException, IOException {

        if (e != null) {
            e.printStackTrace();
        }

        request.setAttribute("error", mensaje);
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
}
