<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.systematic.app.biblioteca.models.Usuario" %>
<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
%>

<!-- Contenido del dashboard -->
<div class="container-fluid pt-3">

    <!-- Bloque de depuración temporal -->
    <c:if test="${estadisticas == null}">
        <div class="alert alert-danger">¡Error! No se recibieron las estadísticas.</div>
    </c:if>

    <c:if test="${estadisticas != null}">
        <div class="alert alert-success">Estadísticas cargadas correctamente.</div>
    </c:if>

    <!-- Encabezado -->
    <div class="row mb-2">
        <div class="col-sm-6">
            <h1>Dashboard</h1>
        </div>
    </div>

    <!-- Fila 1 -->
    <div class="row g-2">
        <!-- Total de Libros -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-info">
                <div class="inner">
                    <h3>${estadisticas.totalLibros}</h3>
                    <p>Total de Libros</p>
                </div>
                <div class="icon">
                    <i class="fas fa-book"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnLibros').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Usuarios Registrados -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-success">
                <div class="inner">
                    <h3>${estadisticas.totalUsuarios}</h3>
                    <p>Usuarios Registrados</p>
                </div>
                <div class="icon">
                    <i class="fas fa-users"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnUsuarios').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Préstamos Activos -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-warning">
                <div class="inner">
                    <h3>${estadisticas.prestamosActivos}</h3>
                    <p>Préstamos Activos</p>
                </div>
                <div class="icon">
                    <i class="fas fa-exchange-alt"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnPrestamos').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Préstamos Vencidos -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-danger">
                <div class="inner">
                    <h3>${estadisticas.prestamosVencidos}</h3>
                    <p>Préstamos Vencidos</p>
                </div>
                <div class="icon">
                    <i class="fas fa-clock"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnPrestamos').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>
    </div>

    <!-- Fila 2 -->
    <div class="row g-2 mt-2">
        <!-- Categorías de Libros -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-primary">
                <div class="inner">
                    <h3>${estadisticas.totalCategorias}</h3>
                    <p>Categorías de Libros</p>
                </div>
                <div class="icon">
                    <i class="fas fa-tags"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnCategorias').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Autores -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-secondary">
                <div class="inner">
                    <h3>${estadisticas.totalAutores}</h3>
                    <p>Autores</p>
                </div>
                <div class="icon">
                    <i class="fas fa-user"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnAutores').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Editoriales -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-teal">
                <div class="inner">
                    <h3>${estadisticas.totalEditoriales}</h3>
                    <p>Editoriales</p>
                </div>
                <div class="icon">
                    <i class="fas fa-building"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnEditoriales').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>

        <!-- Cargos Registrados -->
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
            <div class="small-box bg-dark">
                <div class="inner">
                    <h3>${estadisticas.totalCargos}</h3>
                    <p>Cargos Registrados</p>
                </div>
                <div class="icon">
                    <i class="fas fa-user-tie"></i>
                </div>
                <a href="#" onclick="document.getElementById('btnCargos').click()" class="small-box-footer">
                    Ver más <i class="fas fa-arrow-circle-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>
