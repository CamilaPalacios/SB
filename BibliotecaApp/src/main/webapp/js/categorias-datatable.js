$(document).ready(function () {
    $('#tablaCategorias').DataTable({
        language: {
            url: "${pageContext.request.contextPath}/adminlte/plugins/datatables/Spanish.json"
        }
    });
});



