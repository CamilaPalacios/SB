package com.systematic.app.biblioteca.controllers;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAO;
import com.systematic.app.biblioteca.dao.categorialibro.CategoriaLibroDAOImpl;
import com.systematic.app.biblioteca.models.CategoriaLibro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;

@WebServlet("/CategoriasServlet")
public class CategoriasServlet extends HttpServlet {

    private CategoriaLibroDAO categoriaDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBConnection.getConnection();
            categoriaDAO = new CategoriaLibroDAOImpl(conn);

        } catch (Exception e) {
            throw new ServletException("Error al inicializar DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action == null ? "list" : action) {
                case "list":
                    listarCategorias(request, response);
                    break;
                case "new":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "edit":
                    mostrarFormularioEditar(request, response);
                    break;
                case "view":
                    verCategoria(request, response);
                    break;
                case "delete":
                    eliminarCategoria(request, response);
                    break;
                case "search":
                    buscarCategoria(request, response);
                    break;
                default:
                    listarCategorias(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en operación de base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertarCategoria(request, response);
                    break;
                case "update":
                    actualizarCategoria(request, response);
                    break;
                case "search":
                    buscarCategoria(request, response);
                    break;
                default:
                    listarCategorias(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en operación de base de datos", e);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<CategoriaLibro> categorias = categoriaDAO.listarTodos();
        request.setAttribute("categorias", categorias);
        request.setAttribute("totalCategorias", categorias.size());
        request.getRequestDispatcher("categorias.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("modalNuevaCategoria.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoriaLibro categoria = categoriaDAO.obtenerPorId(id);

        if (categoria != null) {
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("modalEditarCategoria.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Categoría no encontrada");
            listarCategorias(request, response);
        }
    }

    private void verCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoriaLibro categoria = categoriaDAO.obtenerPorId(id);

        if (categoria != null) {
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("verCategoria.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Categoría no encontrada");
            listarCategorias(request, response);
        }
    }

    private void insertarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String nombre = request.getParameter("nombreCategoria");

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            request.setAttribute("error", "El nombre de la categoría es obligatorio");
            listarCategorias(request, response);
            return;
        }

        // Verificar si ya existe
        if (categoriaDAO.existeCategoria(nombre)) {
            request.setAttribute("error", "Ya existe una categoría con este nombre");
            listarCategorias(request, response);
            return;
        }

        CategoriaLibro categoria = new CategoriaLibro();
        categoria.setNombreCategoria(nombre);

        boolean resultado = categoriaDAO.insertar(categoria);

        if (resultado) {
            request.setAttribute("success", "Categoría registrada exitosamente");
        } else {
            request.setAttribute("error", "Error al registrar la categoría");
        }

        response.sendRedirect("CategoriasServlet?success=true");
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("idCategoria"));
        String nombre = request.getParameter("nombreCategoria");

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            request.setAttribute("error", "El nombre de la categoría es obligatorio");
            listarCategorias(request, response);
            return;
        }

        CategoriaLibro categoria = new CategoriaLibro();
        categoria.setIdCategoria(id);
        categoria.setNombreCategoria(nombre);

        boolean resultado = categoriaDAO.actualizar(categoria);

        if (resultado) {
            request.setAttribute("success", "Categoría actualizada exitosamente");
        } else {
            request.setAttribute("error", "Error al actualizar la categoría");
        }

        response.sendRedirect("CategoriasServlet?updated=true");
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Verificar si la categoría tiene libros asociados
        if (categoriaDAO.tieneLibrosAsociados(id)) {
            request.setAttribute("error", "No se puede eliminar la categoría porque tiene libros asociados");
            listarCategorias(request, response);
            return;
        }

        boolean resultado = categoriaDAO.eliminar(id);

        if (resultado) {
            request.setAttribute("success", "Categoría eliminada exitosamente");
        } else {
            request.setAttribute("error", "Error al eliminar la categoría");
        }

        response.sendRedirect("CategoriasServlet?deleted=true");
    }

    private void buscarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String termino = request.getParameter("buscar");

        if (termino == null || termino.trim().isEmpty()) {
            listarCategorias(request, response);
            return;
        }

        List<CategoriaLibro> categorias = categoriaDAO.buscarPorNombre(termino);
        request.setAttribute("categorias", categorias);
        request.setAttribute("totalCategorias", categorias.size());
        request.setAttribute("terminoBusqueda", termino);
        request.getRequestDispatcher("categorias.jsp").forward(request, response);
    }
}
