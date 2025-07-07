$(document).ready(function () {
    // Carga el dashboard por defecto
    $("#contenido-dinamico").load("pages/dashboard-contenido.jsp");

    // Manejo de tarjetas
    $(document).on("click", "#cardAutores", function () {
        $("#contenido-dinamico").load("pages/autores.jsp");
    });

    $(document).on("click", "#cardLibros", function () {
        $("#contenido-dinamico").load("pages/libros.jsp");
    });

    $(document).on("click", "#cardUsuarios", function () {
        $("#contenido-dinamico").load("pages/usuarios.jsp");
    });
});
