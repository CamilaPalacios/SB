$(document).ready(function () {
    // Cargar dashboard.jsp por defecto
    $("#contenido-dinamico").load("dashboard.jsp");

    // Cargar profile.jsp al hacer clic en "Mi Perfil"
    $("#btnPerfil").click(function (e) {
        e.preventDefault();
        $("#contenido-dinamico").load("profile.jsp");
    });
    
    //Ruta base del proyecto
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    // ✅ Cargar categorías y luego ejecutar su JS
    $("#btnCategorias").click(function (e) {
        e.preventDefault();
        $("#contenido-dinamico").load(contextPath + "/categorias", function () {
            // Espera a que se cargue el HTML y luego ejecuta la función
            if (typeof inicializarCategorias === "function") {
                inicializarCategorias();
            } else {
                console.warn("La función inicializarCategorias no está disponible.");
            }
        });
    });
});


