document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loginForm');
    const responseMessage = document.getElementById('loginResponseMessage');
    const apiBaseUrl = 'http://localhost:8080/api/'; // Ensure this is correct

    form.addEventListener('submit', async function(event) {
        event.preventDefault();
        let isValid = true;
        const email = form['email'].value;
        const password = form['password'].value;
        const emailPattern = /^[a-zA-Z0-9._%+-]{3,30}@gmail\.com$/;
        
        clearErrors();
        
        // Email validation
        if (!emailPattern.test(email)) {
            isValid = false;
            showError('email', 'Invalid Email Id');
        }

        // Password validation
        if (password.trim() === '') {
            isValid = false;
            showError('password', 'Password is required');
        }

        if (isValid) {
            try {
                const response = await fetch(`${apiBaseUrl}student/login`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        email: email,
                        password: password
                    })
                });
                const result = await response.json();

                if (response.ok) {
                    showResponseMessage('Login successful!', 'success');
                    // Redirect to profile page or update UI
                    window.location.href = 'profile.html';
                } else {
                    showResponseMessage(result.message || 'Login failed.', 'error');
                }
            } catch (error) {
                console.error('Error:', error);
                showResponseMessage('An error occurred. Please try again later.', 'error');
            }
        }
    });

    function showError(fieldName, message) {
        const input = form[fieldName];
        const errorElement = document.getElementById(`${fieldName}Error`);
        if (input) {
            input.classList.add('error-border');
        }
        if (errorElement) {
            errorElement.textContent = message;
        }
    }

    function clearErrors() {
        document.querySelectorAll('.error-border').forEach(el => el.classList.remove('error-border'));
        document.querySelectorAll('.error-message').forEach(el => el.textContent = '');
    }

    function showResponseMessage(message, type) {
        responseMessage.textContent = message;
        responseMessage.className = 'response-message'; // Reset the class
        if (type === 'error') {
            responseMessage.classList.add('error');
            responseMessage.style.display = 'block';
            setTimeout(() => {
                responseMessage.style.display = 'none'; // Hide after 10 seconds
            }, 10000); // Adjust the time as needed
        } else {
            responseMessage.classList.remove('error');
            responseMessage.style.display = 'block';
            setTimeout(() => {
                responseMessage.style.display = 'none'; // Hide after 10 seconds
            }, 10000); // Adjust the time as needed
        }
    }
});
