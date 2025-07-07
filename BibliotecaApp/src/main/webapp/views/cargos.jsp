<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestión de Cargos</title>
        <!-- CSS DataTables & Bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
    </head>
    <body class="container mt-4">

        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h3 class="card-title mb-0">Mantenimiento de Cargos</h3>
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalNuevoCargo">
                    <i class="fas fa-plus"></i> Nuevo Cargo
                </button>
            </div>

            <div class="card-body">
                <c:if test="${not empty mensajeExito}">
                    <div class="alert alert-success">${mensajeExito}</div>
                </c:if>
                <c:if test="${not empty mensajeError}">
                    <div class="alert alert-danger">${mensajeError}</div>
                </c:if>

                <table id="tablaCargos" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre del Cargo</th>
                            <th style="width:130px;">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cargo" items="${cargos}">
                            <tr>
                                <td>${cargo.idCargo}</td>
                                <td>${cargo.nombreCargo}</td>
                                <td class="text-center">
                                    <!-- Botón Editar -->
                                    <button class="btn btn-warning btn-sm btnEditarCargo"
                                            data-id="${cargo.idCargo}"
                                            data-nombre="${cargo.nombreCargo}"
                                            data-toggle="modal"
                                            data-target="#modalEditarCargo">
                                        <i class="fas fa-edit"></i>
                                    </button>

                                    <!-- Botón Eliminar por POST -->
                                    <form action="${pageContext.request.contextPath}/cargos" method="post" class="d-inline">
                                        <input type="hidden" name="accion" value="eliminar">
                                        <input type="hidden" name="idCargo" value="${cargo.idCargo}">
                                        <button class="btn btn-danger btn-sm" type="submit">
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

        <!-- ========== MODAL: Nuevo Cargo ========== -->
        <div class="modal fade" id="modalNuevoCargo" tabindex="-1">
            <div class="modal-dialog" role="document">
                <form action="${pageContext.request.contextPath}/cargos" method="post" class="modal-content">
                    <input type="hidden" name="accion" value="registrar">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title">Nuevo Cargo</h5>
                        <button class="close" data-dismiss="modal"><span>×</span></button>
                    </div>
                    <div class="modal-body">
                        <label>Nombre del Cargo</label>
                        <input name="nombreCargo" class="form-control" required>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary">Guardar</button>
                        <button class="btn btn-secondary" data-dismiss="modal" type="button">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- ========== MODAL: Editar Cargo ========== -->
        <div class="modal fade" id="modalEditarCargo" tabindex="-1">
            <div class="modal-dialog" role="document">
                <form action="${pageContext.request.contextPath}/cargos" method="post" class="modal-content">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="idCargo" id="edit-idCargo">
                    <div class="modal-header bg-warning">
                        <h5 class="modal-title">Editar Cargo</h5>
                        <button class="close" data-dismiss="modal"><span>×</span></button>
                    </div>
                    <div class="modal-body">
                        <label>Nombre del Cargo</label>
                        <input name="nombreCargo" id="edit-nombreCargo" class="form-control" required>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning">Guardar cambios</button>
                        <button class="btn btn-secondary" data-dismiss="modal" type="button">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- ========== MODAL: Éxito al eliminar ========== -->
        <div class="modal fade" id="modalEliminado" tabindex="-1">
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content text-center">
                    <div class="modal-header bg-success text-white justify-content-center">
                        <h5 class="modal-title">Éxito</h5>
                    </div>
                    <div class="modal-body">
                        <p>✅ Cargo eliminado correctamente.</p>
                        <button class="btn btn-success btn-sm" data-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- SCRIPTS -->
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
                $('#tablaCargos').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                        {
                            extend: 'excelHtml5',
                            className: 'btn btn-success',
                            text: '<i class="fas fa-file-excel"></i> Excel'
                        },
                        {
                            extend: 'pdfHtml5',
                            className: 'btn btn-danger',
                            text: '<i class="fas fa-file-pdf"></i> PDF'
                        }
                    ],
                    language: {
                        url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
                    }
                });

                // Rellenar modal editar
                $('.btnEditarCargo').on('click', function () {
                    const btn = $(this);
                    $('#edit-idCargo').val(btn.data('id'));
                    $('#edit-nombreCargo').val(btn.data('nombre'));
                });

                // Mostrar modal de éxito al eliminar
                if (new URLSearchParams(window.location.search).get('eliminado') === 'exito') {
                    $('#modalEliminado').modal('show');
                    window.history.replaceState({}, '', window.location.pathname);
                }
            });
        </script>

    </body>
</html>
