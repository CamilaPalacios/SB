<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.systematic.app.biblioteca.models.Usuario" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuarioLogueado == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>BibliotecaApp | Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <div class="preloader flex-column justify-content-center align-items-center">
                <img class="animation__shake" src="${pageContext.request.contextPath}/adminlte/dist/img/logoo.png" alt="Logo" height="60" width="60">
            </div>
            <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link" data-toggle="dropdown" href="#">
                            <i class="far fa-user"></i> <%= usuarioLogueado.getNombre()%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a href="${pageContext.request.contextPath}/profile" class="nav-link">
                                <i class="nav-icon fas fa-user"></i> Perfil
                            </a>
                            <div class="dropdown-divider"></div>
                            <a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
                                <i class="fas fa-sign-out-alt mr-2"></i> Cerrar sesión
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <aside class="main-sidebar sidebar-dark-primary elevation-4">
                <a href="#" class="brand-link">
                    <img src="${pageContext.request.contextPath}/adminlte/dist/img/logoo.png" alt="Logo" class="brand-image img-circle elevation-3">
                    <span class="brand-text font-weight-light">BibliotecaApp</span>
                </a>
                <div class="sidebar">
                    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                        <div class="image">
                            <img src="${pageContext.request.contextPath}/adminlte/dist/img/user4-128x128.jpg" class="img-circle elevation-2" alt="User Image">
                        </div>
                        <div class="info">
                            <a href="#" class="d-block">
                                <%= usuarioLogueado.getNombre()%> 
                                <%= usuarioLogueado.getApellidoPaterno()%> 
                                <%= usuarioLogueado.getApellidoMaterno()%>
                            </a>
                        </div>
                    </div>
                    <div class="form-inline">
                        <div class="input-group" data-widget="sidebar-search">
                            <input class="form-control form-control-sidebar" type="search" placeholder="Buscar..." aria-label="Buscar" id="sidebar-search">
                            <div class="input-group-append">
                                <button class="btn btn-sidebar">
                                    <i class="fas fa-search fa-fw"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <nav class="mt-2">
                        <ul class="nav nav-pills nav-sidebar flex-column" id="sidebar-menu" role="menu">
                            <li class="nav-item">
                                <a href="#" class="nav-link active" id="btnDashboard">
                                    <i class="nav-icon fas fa-tachometer-alt"></i>
                                    <p>Dashboard</p>
                                </a>
                            </li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnCategorias"><i class="nav-icon fas fa-tags"></i><p>Categorías</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnAutores"><i class="nav-icon fas fa-user"></i><p>Autores</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnEditoriales"><i class="nav-icon fas fa-building"></i><p>Editoriales</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnLibros"><i class="nav-icon fas fa-book"></i><p>Libros</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnPrestamos"><i class="nav-icon fas fa-handshake"></i><p>Préstamos</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnCargos"><i class="nav-icon fas fa-user-tie"></i><p>Cargos</p></a></li>
                            <li class="nav-item"><a href="#" class="nav-link" id="btnUsuarios"><i class="nav-icon fas fa-users"></i><p>Usuarios</p></a></li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <div class="content-wrapper">
                <section class="content">
                    <div class="container-fluid pt-3">
                        <div id="contenido-dinamico"></div>
                    </div>
                </section>
            </div>
            <footer class="main-footer">
                <strong>&copy; 2025 BibliotecaApp.</strong> Todos los derechos reservados.
            </footer>
        </div>
        <script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>
        <script>
            $(function () {
                const cargarVista = (ruta) => {
                    $("#contenido-dinamico").load(ruta);
                };
                // Cargar por defecto el dashboard
                cargarVista("${pageContext.request.contextPath}/views/dashboard.jsp");

                // Manejo de clics
                $("#btnDashboard").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/dashboard"); //  No pongas .jsp
                });


                $("#btnCategorias").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/categorias");
                });


                $("#btnAutores").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/autores?accion=listar");
                });

                $("#btnEditoriales").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/editoriales?accion=listar");
                });

                $("#btnLibros").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/libros?accion=listar");
                });

                $("#btnPrestamos").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/prestamos");
                });



                $("#btnCargos").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/cargos?accion=listar");
                });

                $("#btnUsuarios").click((e) => {
                    e.preventDefault();
                    cargarVista("${pageContext.request.contextPath}/usuarios?accion=listar");
                });

            });
        </script>
    </body>
</html>
