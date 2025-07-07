<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Recuperar Contraseña</title>
        <link rel="stylesheet" href="adminlte/dist/css/adminlte.min.css">
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <h1><b>Sistema</b> Biblioteca</h1>
                </div>
                <div class="card-body">
                    <p class="login-box-msg">¿Olvidaste tu contraseña? Ingresa tu correo para recuperarla.</p>

                    <form action="${pageContext.request.contextPath}/enviar-recuperacion" method="post">

                        <div class="input-group mb-3">
                            <input type="email" name="email" class="form-control" placeholder="Correo electrónico" required>
                            <div class="input-group-append">
                                <div class="input-group-text"><span class="fas fa-envelope"></span></div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary btn-block">Enviar enlace</button>
                            </div>
                        </div>
                    </form>

                    <%-- Mostrar mensajes opcionales --%>
                    <%
                        String enviado = request.getParameter("enviado");
                        String error = request.getParameter("error");
                        if ("1".equals(enviado)) {
                    %>
                    <div class="alert alert-success mt-3">Se ha enviado un enlace a tu correo.</div>
                    <% } else if ("correo-no-existe".equals(error)) { %>
                    <div class="alert alert-danger mt-3">El correo no está registrado.</div>
                    <% } else if ("servidor".equals(error)) { %>
                    <div class="alert alert-danger mt-3">Error al enviar el correo. Inténtalo más tarde.</div>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
