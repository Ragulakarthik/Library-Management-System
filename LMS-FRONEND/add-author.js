document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('addAuthorForm');
    const addAuthorButton = document.getElementById('addAuthorButton');
    const responseMessage = document.getElementById('responseMessage');
    const apiBaseUrl = 'http://localhost:8080/api/'; // Ensure this is correct

    if (addAuthorButton) {
        addAuthorButton.addEventListener('click', async function() {
            let isValid = true;
            const messages = {
                authorName: '',
                phoneNumber: '',
            };

            clearErrors();

            // Perform validation
            const authorName = form['authorName'].value;
            const namePattern = /^[A-Za-z\s]{1,50}$/; // Author name pattern
            if (!namePattern.test(authorName)) {
                messages.authorName = 'Author name must contain only letters and spaces, up to 50 characters.';
                isValid = false;
                showError('authorName', messages.authorName);
            }

            const phoneNumber = form['phoneNumber'].value;
            const phonePattern = /^[6-9]\d{9}$/; // Phone number pattern
            if (!phonePattern.test(phoneNumber)) {
                messages.phoneNumber = 'Phone number must start with 6, 7, 8, or 9 and be 10 digits long.';
                isValid = false;
                showError('phoneNumber', messages.phoneNumber);
            }

            if (isValid) {
                try {
                    // Send authorName and phoneNumber as query parameters in the URL
                    const response = await fetch(`${apiBaseUrl}author/registerAuthor?authorName=${encodeURIComponent(authorName)}&phoneNumber=${encodeURIComponent(phoneNumber)}`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    const result = await response.json();

                    if (response.ok) {
                        showResponseMessage(result.message || 'Author added successfully!', 'success');
                        resetForm(); // Call the function to reset the form
                    } else {
                        showResponseMessage(result.message || 'Failed to add author.', 'error');
                    }
                } catch (error) {
                    console.error('Error:', error);
                    showResponseMessage('An error occurred. Please try again later.', 'error');
                }
            }
        });
    }

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

    function resetForm() {
        form.reset();
        clearErrors();
    }
});