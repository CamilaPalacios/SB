const loginForm = document.getElementById('loginForm');
const loginModal = new bootstrap.Modal(document.getElementById('loginModal'));
const modalBody = document.getElementById('modalBody');
const closeModalBtn = document.getElementById('closeModalBtn');
const modalIcon = document.getElementById('modalIcon');
const contextPath = document.body.getAttribute('data-context-path');
console.log("Context path: ", contextPath);

loginForm.addEventListener('submit', function (e) {
    e.preventDefault();

    // Creamos un objeto FormData desde el formulario
    const formData = new FormData(loginForm);

    // Convertimos FormData a URLSearchParams para enviar como application/x-www-form-urlencoded
    const params = new URLSearchParams();
    for (const pair of formData) {
        params.append(pair[0], pair[1]);
    }

    fetch(`${contextPath}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: params.toString(),
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    modalIcon.className = "fas fa-check-circle fa-2x text-success";
                    modalBody.textContent = "Inicio de sesion correcto";
                    loginModal.show();

                    closeModalBtn.onclick = () => {
                        window.location.href = `${contextPath}/views/index.jsp`;
                    };
                } else {
                    modalIcon.className = "fas fa-times-circle fa-2x text-danger";
                    modalBody.textContent = "Usuario o password incorrectos.";
                    loginModal.show();
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                modalBody.textContent = "Error al procesar la solicitud.";
                loginModal.show();
            });
});
