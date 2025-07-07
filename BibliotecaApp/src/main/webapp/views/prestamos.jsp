<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Préstamos</title>

    <!-- Estilos de DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<body>
<div class="card">
    <div class="card-header">
        <h3 class="card-title">Mantenimiento de Préstamos</h3>
        <div class="card-tools">
            <a href="${pageContext.request.contextPath}/prestamos/nuevo" class="btn btn-primary">
                <i class="fas fa-plus"></i> Nuevo Préstamo
            </a>
        </div>
    </div>

    <div class="card-body">
        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success">${mensajeExito}</div>
        </c:if>

        <table id="tablaPrestamos" class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>ID Usuario</th>
                <th>ID Libro</th>
                <th>Fecha Préstamo</th>
                <th>Devolución Estimada</th>
                <th>Fecha Devolución</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="prestamo" items="${prestamos}">
                <tr>
                    <td>${prestamo.idPrestamo}</td>
                    <td>${prestamo.idUsuario}</td>
                    <td>${prestamo.idLibro}</td>
                    <td>${prestamo.fechaPrestamo}</td>
                    <td>${prestamo.fechaDevolucionEstimada}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty prestamo.fechaDevolucion}">
                                ${prestamo.fechaDevolucion}
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${prestamo.estado eq 'ACTIVO'}">
                                <span class="badge badge-info">ACTIVO</span>
                            </c:when>
                            <c:when test="${prestamo.estado eq 'DEVUELTO'}">
                                <span class="badge badge-success">DEVUELTO</span>
                            </c:when>
                            <c:when test="${prestamo.estado eq 'VENCIDO'}">
                                <span class="badge badge-danger">VENCIDO</span>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/prestamos/editar?id=${prestamo.idPrestamo}" class="btn btn-warning btn-sm">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/prestamos/devolver?id=${prestamo.idPrestamo}" class="btn btn-danger btn-sm">
                            <i class="fas fa-undo"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Scripts de DataTables -->
<script src="${pageContext.request.contextPath}/adminlte/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/jszip/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/pdfmake/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/pdfmake/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/js/buttons.html5.min.js"></script>

<script>
    $(document).ready(function () {
        $('#tablaPrestamos').DataTable({
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
    });
</script>
</body>
</html>
