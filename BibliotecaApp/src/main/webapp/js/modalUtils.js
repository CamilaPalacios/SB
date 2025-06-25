function mostrarModalLogout() {
    $('#logoutSuccessModal').modal('show');
}

document.addEventListener("DOMContentLoaded", function () {
    const logoutSuccess = document.body.getAttribute("data-logout-success");

    if (logoutSuccess === "true") {
        mostrarModalLogout();
    }
});