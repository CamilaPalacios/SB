<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registrar Usuario</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/adminlte.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="card card-outline card-primary">
        <div class="card-header text-center">
            <h1 class="h1 text-center"><b>Sistema</b>Biblioteca</h1>
        </div>
        <div class="card-body">
            <p class="login-box-msg">Registrar un Nuevo Usuario</p>

            <c:if test="${not empty errorCreacion}">
                <div class="alert alert-danger">${errorCreacion}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/usuarios" method="post">
                <input type="hidden" name="accion" value="registrar">

                <div class="input-group mb-3">
                    <input type="text" name="txtNombre" class="form-control" placeholder="Ingrese su Nombre" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-user"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" name="txtApellidoPaterno" class="form-control" placeholder="Ingrese su Apellido Paterno" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-user"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" name="txtApellidoMaterno" class="form-control" placeholder="Ingrese su Apellido Materno" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-user"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" name="txtCelular" class="form-control" placeholder="Ingrese su Número de Celular">
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-phone"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="email" class="form-control" name="txtEmail" placeholder="Email" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-envelope"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" name="txtNickName" class="form-control" placeholder="Ingrese su Nickname" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-user-tag"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <select name="idCargo" class="form-control" required>
                        <option value="">-- Seleccione un Cargo --</option>
                        <c:forEach var="cargo" items="${cargos}">
                            <option value="${cargo.idCargo}">${cargo.nombreCargo}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ✅ Campos nuevos -->
                <div class="input-group mb-3">
                    <input type="text" name="txtDireccion" class="form-control" placeholder="Ingrese su Dirección">
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-map-marker-alt"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" name="txtEducacion" class="form-control" placeholder="Ingrese su Nivel de Educación">
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-graduation-cap"></span></div>
                    </div>
                </div>

                <!-- Passwords -->
                <div class="input-group mb-3">
                    <input type="password" name="txtPassword" class="form-control" placeholder="Password" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-lock"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" name="txtRepetirPassword" class="form-control" placeholder="Repetir Password" required>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-lock"></span></div>
                    </div>
                </div>
                <c:if test="${not empty errorPassword}">
                    <small class="text-danger">${errorPassword}</small>
                </c:if>

                <div class="row">
                    <div class="col-4">
                        <button type="submit" class="btn btn-primary btn-block">Registrar</button>
                    </div>
                </div>
            </form>

            <a href="${pageContext.request.contextPath}/login.jsp" class="text-center">Ya tengo un usuario</a>
        </div>
    </div>
</div>

<!-- Modal éxito -->
<div class="modal fade" id="modalExito" tabindex="-1" role="dialog" aria-labelledby="modalExitoLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="modalExitoLabel">¡Éxito!</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">Usuario creado correctamente.</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>

<c:if test="${usuarioCreado}">
    <script>
        $(document).ready(function () {
            $("#modalExito").modal("show");
        });
    </script>
</c:if>
</body>
</html>
