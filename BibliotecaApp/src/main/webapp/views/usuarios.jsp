<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Usuarios</title>

    <!-- Bootstrap 4 (AdminLTE 3 usa B4) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/css/bootstrap.min.css">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<body class="container-fluid mt-3">

<!-- ==================== CARD LISTADO ==================== -->
<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h3 class="card-title mb-0">Mantenimiento de Usuarios</h3>
        <button class="btn btn-primary" data-toggle="modal" data-target="#modalNuevoUsuario">
            <i class="fas fa-plus"></i> Nuevo Usuario
        </button>
    </div>

    <div class="card-body">

        <!-- Alertas por errores (registro) -->
        <c:if test="${not empty errorCreacion}">
            <div class="alert alert-danger">${errorCreacion}</div>
        </c:if>
        <c:if test="${not empty errorPassword}">
            <div class="alert alert-danger">${errorPassword}</div>
        </c:if>

        <!-- =================== TABLA =================== -->
        <table id="tablaUsuarios" class="table table-bordered table-striped">
            <thead class="bg-light">
            <tr>
                <th>ID</th>
                <th>Nombre Completo</th>
                <th>Nickname</th>
                <th>Email</th>
                <th>Celular</th>
                <th>Cargo</th>
                <th style="width:130px;">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.idUsuario}</td>
                    <td>${u.nombre} ${u.apellidoPaterno} ${u.apellidoMaterno}</td>
                    <td>${u.nickName}</td>
                    <td>${u.email}</td>
                    <td>${u.telefonoCelular}</td>
                    <td>${u.cargo.nombreCargo}</td>
                    <td class="text-center">

                        <!-- ✏️ EDITAR -->
                        <button type="button" class="btn btn-warning btn-sm btnEditar"
                                data-id="${u.idUsuario}"
                                data-nombre="${u.nombre}"
                                data-apepat="${u.apellidoPaterno}"
                                data-apemat="${u.apellidoMaterno}"
                                data-nick="${u.nickName}"
                                data-email="${u.email}"
                                data-celular="${u.telefonoCelular}"
                                data-direccion="${u.direccion}"
                                data-educacion="${u.educacion}"
                                data-idcargo="${u.cargo.idCargo}"
                                data-toggle="modal" data-target="#modalEditarUsuario">
                            <i class="fas fa-edit"></i>
                        </button>

                        <!-- 🗑️ ELIMINAR (POST) -->
                        <form action="${pageContext.request.contextPath}/usuarios" method="post" class="d-inline">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="id" value="${u.idUsuario}">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="fas fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!--  MODAL NUEVO USUARIO                                -->
<div class="modal fade" id="modalNuevoUsuario" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <form action="${pageContext.request.contextPath}/usuarios" method="post" class="modal-content">
      <input type="hidden" name="accion" value="registrar">

      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title">Registrar Nuevo Usuario</h5>
        <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
      </div>

      <div class="modal-body row">
        <!-- campos -->
        <div class="col-md-4"><label>Nombres</label><input name="txtNombre" class="form-control" required></div>
        <div class="col-md-4"><label>Apellido Paterno</label><input name="txtApellidoPaterno" class="form-control" required></div>
        <div class="col-md-4"><label>Apellido Materno</label><input name="txtApellidoMaterno" class="form-control" required></div>

        <div class="col-md-4"><label>Nickname</label><input name="txtNickName" class="form-control" required></div>
        <div class="col-md-4"><label>Email</label><input type="email" name="txtEmail" class="form-control" required></div>
        <div class="col-md-4"><label>Celular</label><input name="txtCelular" class="form-control"></div>

        <div class="col-md-4"><label>Dirección</label><input name="txtDireccion" class="form-control"></div>
        <div class="col-md-4"><label>Educación</label><input name="txtEducacion" class="form-control"></div>

        <div class="col-md-4">
            <label>Cargo</label>
            <select name="idCargo" class="form-control" required>
                <c:forEach var="c" items="${cargos}">
                    <option value="${c.idCargo}">${c.nombreCargo}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-6"><label>Contraseña</label><input type="password" name="txtPassword" class="form-control" required></div>
        <div class="col-md-6"><label>Repetir Contraseña</label><input type="password" name="txtRepetirPassword" class="form-control" required></div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-primary" type="submit">Guardar</button>
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
      </div>
    </form>
  </div>
</div>

<!--  MODAL EDITAR USUARIO                               -->
<div class="modal fade" id="modalEditarUsuario" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <form action="${pageContext.request.contextPath}/usuarios" method="post" class="modal-content">
      <input type="hidden" name="accion" value="actualizar"><!-- (para futuro) -->
      <input type="hidden" name="idUsuario" id="edit-id">

      <div class="modal-header bg-warning">
        <h5 class="modal-title">Editar Usuario</h5>
        <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
      </div>

      <div class="modal-body row">
        <!-- mismos campos, con id="edit-*" -->
        <div class="col-md-4"><label>Nombres</label><input id="edit-nombre" name="txtNombre" class="form-control" required></div>
        <div class="col-md-4"><label>Apellido Paterno</label><input id="edit-apepat" name="txtApellidoPaterno" class="form-control" required></div>
        <div class="col-md-4"><label>Apellido Materno</label><input id="edit-apemat" name="txtApellidoMaterno" class="form-control" required></div>

        <div class="col-md-4"><label>Nickname</label><input id="edit-nick" name="txtNickName" class="form-control" required></div>
        <div class="col-md-4"><label>Email</label><input id="edit-email" name="txtEmail" type="email" class="form-control" required></div>
        <div class="col-md-4"><label>Celular</label><input id="edit-celular" name="txtCelular" class="form-control"></div>

        <div class="col-md-4"><label>Dirección</label><input id="edit-direccion" name="txtDireccion" class="form-control"></div>
        <div class="col-md-4"><label>Educación</label><input id="edit-educacion" name="txtEducacion" class="form-control"></div>

        <div class="col-md-4">
            <label>Cargo</label>
            <select id="edit-cargo" name="idCargo" class="form-control" required>
                <c:forEach var="c" items="${cargos}">
                    <option value="${c.idCargo}">${c.nombreCargo}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-6"><label>Nueva contraseña (opcional)</label><input type="password" name="txtPassword" class="form-control"></div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-warning" type="submit">Guardar cambios</button>
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
      </div>
    </form>
  </div>
</div>

<!--  MODAL ÉXITO ELIMINACIÓN                            -->
<div class="modal fade" id="modalEliminado" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
    <div class="modal-content text-center">
      <div class="modal-header bg-success text-white justify-content-center">
        <h5 class="modal-title">Éxito</h5>
      </div>
      <div class="modal-body">
        <p>✅ Usuario eliminado correctamente.</p>
        <button class="btn btn-success btn-sm" data-dismiss="modal">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- ==================== SCRIPTS ==================== -->
<script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/jszip/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/pdfmake/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/pdfmake/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/js/buttons.html5.min.js"></script>

<script>
$(function () {
    /* -------- DataTables -------- */
    $('#tablaUsuarios').DataTable({
        dom: 'Bfrtip',
        buttons: [
            {extend: 'excelHtml5', className: 'btn btn-success', text: '<i class="fas fa-file-excel"></i> Excel'},
            {extend: 'pdfHtml5',   className: 'btn btn-danger',  text: '<i class="fas fa-file-pdf"></i> PDF'}
        ],
        language: {url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'}
    });

    /* -------- Llenar Modal Editar -------- */
    $('.btnEditar').on('click', function () {
        const b = $(this);
        $('#edit-id').val(        b.data('id'));
        $('#edit-nombre').val(    b.data('nombre'));
        $('#edit-apepat').val(    b.data('apepat'));
        $('#edit-apemat').val(    b.data('apemat'));
        $('#edit-nick').val(      b.data('nick'));
        $('#edit-email').val(     b.data('email'));
        $('#edit-celular').val(   b.data('celular'));
        $('#edit-direccion').val( b.data('direccion'));
        $('#edit-educacion').val( b.data('educacion'));
        $('#edit-cargo').val(     b.data('idcargo'));
    });

    /* -------- Mostrar modal éxito eliminación si corresponde -------- */
    if (new URLSearchParams(window.location.search).get('eliminado') === 'exito') {
        $('#modalEliminado').modal('show');
        /* Limpia la URL (opcional): quita ?eliminado=exito sin recargar */
        const cleanUrl = window.location.pathname;
        window.history.replaceState({}, '', cleanUrl);
    }
});
</script>

</body>
</html>
