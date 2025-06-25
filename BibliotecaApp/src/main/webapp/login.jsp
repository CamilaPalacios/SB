<jsp:include page="views/modals/modalLogin.jsp" />
<jsp:include page="views/modals/modalLogoutSuccess.jsp" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LOGIN</title>

        <!-- Fuentes Google -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">

        <!-- iCheck Bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

        <!-- Tema principal de AdminLTE -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">
    </head>
    <body class="hold-transition login-page"
          data-context-path="${pageContext.request.contextPath}"
          data-logout-success="<%= "success".equals(request.getParameter("logout"))%>">
        <div class="login-box">
            <!-- /.login-logo -->
            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <a href="../../index2.html" class="h1"><b>Sistema</b>Biblioteca</a>
                </div>
                <div class="card-body">
                    <p class="login-box-msg">Ingresa tu Usuario y Contraseña para Iniciar Sesión</p>

                    <form action="${pageContext.request.contextPath}/login" method="post" id="loginForm">
                        <div class="input-group mb-3">
                            <input type="text" name="txtNickName" class="form-control" placeholder="Usuario">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
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
                                    <input type="checkbox" id="remember">
                                    <label for="remember">
                                        Recuerdame
                                    </label>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-4">
                                <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
                            </div>
                            <!-- /.col -->
                        </div>
                    </form>
                    <p class="mb-1">
                        <a href="views/forgotPassword.jsp">Olvidé mi Contraseña</a>
                    </p>
                    <p class="mb-0">
                        <a href="${pageContext.request.contextPath}/usuarios" class="text-center">Registrar un Nuevo Usuario</a>
                    </p>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
        <!-- /.login-box -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>

        <!-- Tu script de login -->
        <script src="${pageContext.request.contextPath}/js/login.js"></script>

        <!--Scripts utilitarios -->
        <script src="${pageContext.request.contextPath}/js/modalUtils.js"></script>

    </body>
</html>
