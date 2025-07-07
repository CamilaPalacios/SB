<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Libros</title>

    <!-- Estilos DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<body>
<div class="card">
    <div class="card-header">
        <h3 class="card-title">Mantenimiento de Libros</h3>
        <div class="card-tools">
            <button id="btnNuevoLibro" class="btn btn-primary"><i class="fas fa-plus"></i> Nuevo Libro</button>
        </div>
    </div>

    <div class="card-body">
        <table id="tablaLibros" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Año</th>
                    <th>ISBN</th>
                    <th>Cantidad</th>
                    <th>Autor</th>
                    <th>Categoría</th>
                    <th>Editorial</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="libro" items="${libros}">
                    <tr>
                        <td>${libro.idLibro}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.anioPublicacion}</td>
                        <td>${libro.isbn}</td>
                        <td>${libro.cantidad}</td>
                        <td>
                            <c:out value="${libro.autor.nombreAutor} ${libro.autor.apellidoPaterno}" />
                        </td>
                        <td>${libro.categoria.nombreCategoria}</td>
                        <td>${libro.editorial.nombreEditorial}</td>
                        <td>
                            <button class="btn btn-warning btn-sm btnEditarLibro" data-id="${libro.idLibro}">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="btn btn-danger btn-sm btnEliminarLibro" data-id="${libro.idLibro}">
                                <i class="fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Scripts DataTables -->
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
        $('#tablaLibros').DataTable({
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'excelHtml5',
                    className: 'btn btn-success',
                    text: '<i class="fas fa-file-excel"></i> Exportar a Excel'
                },
                {
                    extend: 'pdfHtml5',
                    className: 'btn btn-danger',
                    text: '<i class="fas fa-file-pdf"></i> Exportar PDF'
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
