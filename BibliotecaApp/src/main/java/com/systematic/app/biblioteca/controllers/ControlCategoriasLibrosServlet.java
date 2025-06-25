package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAOImpl;
import com.systematic.app.biblioteca.models.CategoriaLibro;
import com.systematic.app.biblioteca.services.categorialibro.CategoriaLibroService;
import com.systematic.app.biblioteca.services.categorialibro.CategoriaLibroServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anthony
 */
@WebServlet("/categorias")
public class ControlCategoriasLibrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            CategoriaLibroDAO categoriaLibroDAO = new CategoriaLibroDAOImpl(connection);
            CategoriaLibroService categoriaLibroService = new CategoriaLibroServiceImpl(categoriaLibroDAO);
            List<CategoriaLibro> categorias = categoriaLibroService.findAll();
            if (categorias == null || categorias.isEmpty()) {
                req.setAttribute("mensaje", "No hay categorias registradas");
            }
            req.setAttribute("categorias", categorias);
            req.getRequestDispatcher("/views/categorias.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar categorías");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada.");
            return;
        }

        try (Connection connection = DBConnection.getConnection()) {
            CategoriaLibroDAO categoriaLibroDAO = new CategoriaLibroDAOImpl(connection);
            switch (accion) {

                case "registrar": {
                    String nombreCategoria = req.getParameter("txtNombreCategoria");
                    if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El Nombre de la categoría es obligatorio");
                        return;
                    }
                    CategoriaLibroService categoriaLibroService = new CategoriaLibroServiceImpl(categoriaLibroDAO);
                    CategoriaLibro categoriaLibro = new CategoriaLibro();
                    categoriaLibro.setNombreCategoria(nombreCategoria.trim());
                    categoriaLibroService.insertarCategoriaLibro(categoriaLibro);
                    recargarCategorias(req, resp);
                    break;
                }
                case "editar": {
                    Integer idCategoria = Integer.valueOf(req.getParameter("IdCategoria"));
                    String nombreCategoria = req.getParameter("txtNombreCategoria");
                    if (idCategoria == null || nombreCategoria == null) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos para editar la categoría");
                        return;
                    }
                    CategoriaLibroService categoriaLibroService = new CategoriaLibroServiceImpl(categoriaLibroDAO);
                    CategoriaLibro categoriaLibro = new CategoriaLibro();
                    categoriaLibro.setIdCategoria(idCategoria);
                    categoriaLibro.setNombreCategoria(nombreCategoria.trim());
                    categoriaLibroService.actualizarCategoriaLibro(categoriaLibro);
                    recargarCategorias(req, resp);
                    break;
                }
                case "eliminar": {
                    Integer idCategoria = Integer.valueOf(req.getParameter("idCategoria"));
                    if (idCategoria == null) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "el código de la categoría es requerido");
                        return;
                    }
                    CategoriaLibroService categoriaLibroService = new CategoriaLibroServiceImpl(categoriaLibroDAO);
                    categoriaLibroService.eliminarCategoriaLibro(idCategoria);
                    recargarCategorias(req, resp);
                    break;
                }
                default: {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos para editar la categoría");
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro al registrar la categoría");
        }
    }

    private void recargarCategorias(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Redirige al mismo servlet para recargar la lista
            resp.sendRedirect(req.getContextPath() + "/categorias");
        } catch (IOException ex) {
            Logger.getLogger(ControlCategoriasLibrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
