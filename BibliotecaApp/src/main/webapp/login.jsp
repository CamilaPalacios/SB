<%@ page import="jakarta.servlet.http.Cookie" %>
<%
    String nicknameRecordado = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("recordarUsuario".equals(cookie.getName())) {
                nicknameRecordado = cookie.getValue();
            }
        }
    }
%>

<jsp:include page="views/modals/modalLogin.jsp" />
<jsp:include page="views/modals/modalLogoutSuccess.jsp" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LOGIN</title>

        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">

        <!-- iCheck Bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

        <!-- AdminLTE Theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">

        <!-- Tu CSS personalizado (puedes usar login.css para estilo futurista) -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    </head>

    <body class="hold-transition login-page"
          data-context-path="${pageContext.request.contextPath}"
          data-logout-success="<%= "success".equals(request.getParameter("logout"))%>">

        <div class="login-box">
            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <h1 class="h1 text-center"><b>Sistema</b>Biblioteca</h1>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/login" method="post" id="loginForm">
                        <div class="input-group mb-3">
                            <input type="text" name="txtNickName" class="form-control"
                                   placeholder="Usuario" value="<%= nicknameRecordado%>">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-user"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <input type="password" name="txtPassword" class="form-control" placeholder="Password">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-8">
                                <div class="icheck-primary">
                                    <input type="checkbox" id="remember" name="remember"
                                           <%= !nicknameRecordado.isEmpty() ? "checked" : ""%>>
                                    <label for="remember">
                                        Recuérdame
                                    </label>
                                </div>
                            </div>
                            <div class="col-4">
                                <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
                            </div>
                        </div>
                    </form>

                    <p class="mb-1">
                        <a href="views/forgotPassword.jsp">Olvidé mi Contraseña</a>
                    </p>
                    <p class="mb-0">
                        <a href="${pageContext.request.contextPath}/usuarios" class="text-center">Registrar un Nuevo Usuario</a>
                    </p>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- AdminLTE -->
        <script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>

        <!-- Tu JS -->
        <script src="${pageContext.request.contextPath}/js/login.js"></script>
        <script src="${pageContext.request.contextPath}/js/modalUtils.js"></script>

    </body>
</html>
