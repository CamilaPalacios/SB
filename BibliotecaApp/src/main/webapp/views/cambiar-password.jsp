<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cambiar Contraseña</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cambio.css">

    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <h1><b>Sistema</b> Biblioteca</h1>
                </div>
                <div class="card-body">
                    <p class="login-box-msg">Establece tu nueva contraseña</p>

                    <form action="${pageContext.request.contextPath}/recuperar-password" method="post">
                        <input type="hidden" name="token" value="${param.token}" />

                        <div class="input-group mb-3">
                            <input type="password" name="nuevaPassword" class="form-control" placeholder="Nueva contraseña" required>
                            <div class="input-group-append">
                                <div class="input-group-text"><span class="fas fa-lock"></span></div>
                            </div>
                        </div>

                        <div class="input-group mb-3">
                            <input type="password" name="confirmarPassword" class="form-control" placeholder="Confirmar contraseña" required>
                            <div class="input-group-append">
                                <div class="input-group-text"><span class="fas fa-lock"></span></div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary btn-block">Cambiar Contraseña</button>
                            </div>
                        </div>
                    </form>

                    <%-- Mensajes de error --%>
                    <%
                        String error = request.getParameter("error");
                        if ("1".equals(error)) {
                    %>
                    <div class="alert alert-danger mt-3">Las contraseñas no coinciden.</div>
                    <% } else if ("2".equals(error)) { %>
                    <div class="alert alert-warning mt-3">El token es inválido o ha expirado.</div>
                    <% } else if ("3".equals(error)) { %>
                    <div class="alert alert-danger mt-3">Error en el servidor.</div>
                    <% }%>
                </div>
            </div>
        </div>
        <script>
            window.addEventListener('DOMContentLoaded', () => {
                const form = document.querySelector("form");
                if (form) {
                    form.addEventListener("submit", function (e) {
                        const password = document.querySelector("input[name='nuevaPassword']").value;
                        const confirm = document.querySelector("input[name='confirmarPassword']").value;

                        if (password !== confirm) {
                            e.preventDefault(); // Detiene el envío
                            alert("Las contraseñas no coinciden.");
                        }
                    });
                }

                // Limpiar el parámetro ?error=X después de mostrarlo
                const url = new URL(window.location.href);
                if (url.searchParams.has('error')) {
                    url.searchParams.delete('error');
                    window.history.replaceState({}, document.title, url.pathname + url.search + url.hash);
                }
            });
        </script>


    </body>
</html>

