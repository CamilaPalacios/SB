$(document).ready(function () {
    // Cargar dashboard.jsp por defecto
    $("#contenido-dinamico").load("dashboard.jsp");

    // Cargar profile.jsp al hacer clic en "Mi Perfil"
    $("#btnPerfil").click(function (e) {
        e.preventDefault();
        $("#contenido-dinamico").load("profile.jsp");
    });
});


