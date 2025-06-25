<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.systematic.app.biblioteca.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
%>

<!-- Content Header (Page header) -->
<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Perfil</h1>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Inicio</a></li>
                    <li class="breadcrumb-item active">Perfil</li>
                </ol>
            </div>
        </div>
    </div>
</section>

<!-- Main content -->
<section class="content">
    <div class="d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="col-md-6">
            <!-- Profile Image -->
            <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                    <div class="text-center">
                        <img class="profile-user-img img-fluid img-circle"
                             src="${pageContext.request.contextPath}/adminlte/dist/img/user4-128x128.jpg"
                             alt="Foto de usuario">
                    </div>

                    <h3 class="profile-username text-center">
                        <%= usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno()%>
                    </h3>

                    <p class="text-muted text-center"><%=usuario.getCargo().getNombreCargo() %></p>
                    <a href="#" class="btn btn-primary btn-block"><b>Editar</b></a>
                </div>
            </div>

            <!-- About Me Box -->
            <div class="card card-primary">
                <div class="card-header">
                    <h3 class="card-title">Sobre mí</h3>
                </div>
                <div class="card-body">
                    <strong><i class="fas fa-user mr-1"></i> Nombres y Apellidos</strong>
                    <p class="text-muted"><%= usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno()%></p>

                    <strong><i class="fas fa-phone mr-1"></i> Celular</strong>
                    <p class="text-muted"><%=usuario.getTelefonoCelular()!=null ? usuario.getTelefonoCelular(): ""%></p>

                    <strong><i class="fas fa-envelope mr-1"></i> Email</strong>
                    <p class="text-muted"><%=usuario.getEmail()%></p>

                    <strong><i class="fas fa-graduation-cap mr-1"></i> Educación</strong>
                    <p class="text-muted"><%=usuario.getEducacion()!=null ? usuario.getEducacion() : ""%></p>

                    <strong><i class="fas fa-map-marker-alt mr-1"></i> Dirección</strong>
                    <p class="text-muted"><%=usuario.getDireccion()!=null ? usuario.getDireccion() : ""%></p>
                </div>
            </div>
        </div>
    </div>
</section>
