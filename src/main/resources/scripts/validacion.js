	document.addEventListener('DOMContentLoaded', () => {
        const form = document.querySelector('form');
        const nombre = document.getElementById('nombre');
        const apellidos = document.getElementById('apellidos');
        const email = document.getElementById('email');
        const direccion = document.getElementById('direccion');
        const dni = document.getElementById('dni');
        const telefono = document.getElementById('tlf');
        const password = document.getElementById('password');
        const confirmPassword = document.getElementById('confirm-password');

        form.addEventListener('submit', (e) => {
            let isValid = true;
            let errorMessage = "";

            // Validar nombre
            if (nombre.value.trim() === "") {
                isValid = false;
                errorMessage += "El campo 'Nombre' es obligatorio.\n";
            }

            // Validar apellidos
            if (apellidos.value.trim() === "") {
                isValid = false;
                errorMessage += "El campo 'Apellidos' es obligatorio.\n";
            }

            // Validar email
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email.value)) {
                isValid = false;
                errorMessage += "Por favor, introduce un correo electrónico válido.\n";
            }

            // Validar dirección
            if (direccion.value.trim() === "") {
                isValid = false;
                errorMessage += "El campo 'Dirección' es obligatorio.\n";
            }

            // Validar DNI
            const dniRegex = /^[0-9]{8}[A-Za-z]$/;
            if (!dniRegex.test(dni.value)) {
                isValid = false;
                errorMessage += "El DNI debe tener 8 números seguidos de una letra.\n";
            }

            // Validar teléfono
            const telefonoRegex = /^[0-9]{9}$/;
            if (!telefonoRegex.test(telefono.value)) {
                isValid = false;
                errorMessage += "El teléfono debe tener 9 dígitos.\n";
            }

            // Validar contraseña
            if (password.value.trim() === "") {
                isValid = false;
                errorMessage += "El campo 'Contraseña' es obligatorio.\n";
            } else if (password.value !== confirmPassword.value) {
                isValid = false;
                errorMessage += "Las contraseñas no coinciden.\n";
            }

            // Mostrar errores si no es válido
            if (!isValid) {
                e.preventDefault(); // Evita que el formulario se envíe
                alert(errorMessage);
            }
        });
    });
