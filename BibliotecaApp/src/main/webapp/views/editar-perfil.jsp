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
    <title>Editar Perfil</title>
    <!-- AdminLTE CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="hold-transition layout-top-nav">

<div class="wrapper">

    <!-- Encabezado -->
    <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
        <div class="container">
            <a href="${pageContext.request.contextPath}/views/index.jsp" class="navbar-brand">
                <span class="brand-text font-weight-light">Sistema Biblioteca</span>
            </a>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="content-wrapper d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <div class="card card-primary">
                        <div class="card-header text-center">
                            <h3 class="card-title w-100">Editar Perfil</h3>
                        </div>
                        <form action="${pageContext.request.contextPath}/actualizar-perfil" method="post">
                            <div class="card-body">

                                <div class="form-group">
                                    <label>Nombres</label>
                                    <input type="text" class="form-control" name="nombre" value="<%= usuario.getNombre() %>" required>
                                </div>

                                <div class="form-group">
                                    <label>Apellido Paterno</label>
                                    <input type="text" class="form-control" name="apellidoPaterno" value="<%= usuario.getApellidoPaterno() %>" required>
                                </div>

                                <div class="form-group">
                                    <label>Apellido Materno</label>
                                    <input type="text" class="form-control" name="apellidoMaterno" value="<%= usuario.getApellidoMaterno() %>" required>
                                </div>

                                <div class="form-group">
                                    <label>Celular</label>
                                    <input type="text" class="form-control" name="telefonoCelular" value="<%= usuario.getTelefonoCelular() %>">
                                </div>

                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="email" class="form-control" name="email" value="<%= usuario.getEmail() %>">
                                </div>

                                <div class="form-group">
                                    <label>Usuario (nickname)</label>
                                    <input type="text" class="form-control" name="nickName" value="<%= usuario.getNickName() %>" required>
                                </div>

                                <div class="form-group">
                                    <label>Contraseña</label>
                                    <input type="password" class="form-control" name="password" value="<%= usuario.getPassword() %>" required>
                                </div>

                                <div class="form-group">
                                    <label>Dirección</label>
                                    <input type="text" class="form-control" name="direccion" value="<%= usuario.getDireccion() %>">
                                </div>

                                <div class="form-group">
                                    <label>Educación</label>
                                    <input type="text" class="form-control" name="educacion" value="<%= usuario.getEducacion() %>">
                                </div>

                                <div class="form-group">
                                    <label>Cargo</label>
                                    <select class="form-control" name="idCargo" required>
                                        <option value="1" <%= usuario.getCargo().getIdCargo() == 1 ? "selected" : "" %>>Administrador</option>
                                        <option value="2" <%= usuario.getCargo().getIdCargo() == 2 ? "selected" : "" %>>Bibliotecario</option>
                                        <option value="3" <%= usuario.getCargo().getIdCargo() == 3 ? "selected" : "" %>>Asistente</option>
                                    </select>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                <a href="${pageContext.request.contextPath}/views/profile.jsp" class="btn btn-secondary">Cancelar</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- JS de AdminLTE -->
<script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>

</body>
</html>
