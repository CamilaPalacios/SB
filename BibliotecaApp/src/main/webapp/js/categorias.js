
function inicializarCategorias() {
    //MOSTRAR DATATABLE
    $('#tablaCategorias').DataTable({
        language: {
            url: window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/adminlte/plugins/datatables/Spanish.json"
        }
    });

    //MOSTRAR MODAL PARA EDITAR DATOS
    $(document).on('click', '.btnEditar', function () {
        const id = $(this).data('id');
        const nombre = $(this).data('nombre');
        $('#editIdCategoria').val(id);
        $('#editNombreCategoria').val(nombre);
        $('#modalEditarCategoria').modal('show');
    });

    //MOSTRAR  MODAL PARA REGISTRAR UNA NUEVA CATEGORIA
    $(document).on('click', '#btnNuevaCategoria', function () {
        $('#modalNuevaCategoria').modal('show');
    });

    $('#formEditarCategoria').submit(function (e) {
        e.preventDefault();
        const id = $('#editIdCategoria').val();
        const nombre = $('#editNombreCategoria').val();

        // Aquí va tu AJAX o redirección
        console.log('Editar: ', id, nombre);
    });

    // Enviar formulario de nueva categoría vía AJAX
    $('#formNuevaCategoria').submit(function (e) {
        e.preventDefault();

        const nombreCategoria = $('#txtNombreCategoria').val();

        $.ajax({
            url: window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/categorias",
            method: "POST",
            data: {
                accion: "registrar",
                txtNombreCategoria: nombreCategoria
            },
            success: function (response) {
                $('#modalNuevaCategoria').modal('hide');
                // Mostrar alerta SweetAlert
                Swal.fire({
                    icon: 'success',
                    title: '¡Categoría registrada!',
                     text: `La categoría "${nombreCategoria}" fue registrada correctamente.`,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'OK'
                });

                $("#btnCategorias").trigger("click"); // Recarga la vista
            },
            error: function (xhr, status, error) {
                console.error("Error al registrar categoría:", error);
                alert("Hubo un error al registrar la categoría.");
            }
        });
    });
}