<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.systematic.app.biblioteca.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario</title>

    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">

    <!-- AdminLTE CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">

    <!-- Bootstrap (ya está incluido en AdminLTE, pero se puede cargar por separado si lo necesitas) -->
</head>
<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">
    <!-- Contenido principal -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Perfil</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/index.jsp">Inicio</a></li>
                        <li class="breadcrumb-item active">Perfil</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>

    <section class="content">
        <div class="d-flex justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="col-md-6">
                <!-- Imagen de perfil -->
                <div class="card card-primary card-outline">
                    <div class="card-body box-profile">
                        <div class="text-center">
                            <img class="profile-user-img img-fluid img-circle"
                                 src="${pageContext.request.contextPath}/adminlte/dist/img/user4-128x128.jpg"
                                 alt="Foto de usuario">
                        </div>

                        <h3 class="profile-username text-center">
                            <%= usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno() %>
                        </h3>

                        <p class="text-muted text-center">
                            <%= usuario.getCargo() != null ? usuario.getCargo().getNombreCargo() : "Cargo no asignado" %>
                        </p>

                        <a href="${pageContext.request.contextPath}/views/editar-perfil.jsp" class="btn btn-primary btn-block"><b>Editar</b></a>
                    </div>
                </div>

                <!-- Datos personales -->
                <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">Sobre mí</h3>
                    </div>
                    <div class="card-body">
                        <strong><i class="fas fa-user mr-1"></i> Nombres y Apellidos</strong>
                        <p class="text-muted">
                            <%= usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno() %>
                        </p>

                        <hr>
                        <strong><i class="fas fa-phone mr-1"></i> Celular</strong>
                        <p class="text-muted">
                            <%= usuario.getTelefonoCelular() != null && !usuario.getTelefonoCelular().isEmpty() ? usuario.getTelefonoCelular() : "No registrado" %>
                        </p>

                        <hr>
                        <strong><i class="fas fa-envelope mr-1"></i> Email</strong>
                        <p class="text-muted">
                            <%= usuario.getEmail() != null ? usuario.getEmail() : "No registrado" %>
                        </p>

                        <hr>
                        <strong><i class="fas fa-graduation-cap mr-1"></i> Educación</strong>
                        <p class="text-muted">
                            <%= usuario.getEducacion() != null && !usuario.getEducacion().isEmpty() ? usuario.getEducacion() : "No especificado" %>
                        </p>

                        <hr>
                        <strong><i class="fas fa-map-marker-alt mr-1"></i> Dirección</strong>
                        <p class="text-muted">
                            <%= usuario.getDireccion() != null && !usuario.getDireccion().isEmpty() ? usuario.getDireccion() : "No especificada" %>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>
</body>
</html>
