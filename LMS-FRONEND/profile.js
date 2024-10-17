document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('profileForm');
    const updateButton = document.getElementById('updateButton');
    const responseMessage = document.getElementById('responseMessage');
    const apiBaseUrl = 'http://localhost:8080/api/'; // Ensure this is correct

    if (updateButton) {
        updateButton.addEventListener('click', async function() {
            let isValid = true;
            const messages = {
                firstName: '',
                lastName: '',
                email: '',
                phoneNumber: '',
                course: '',
                branch: ''
            };

            clearErrors();

            // Perform validation
            const firstName = form['firstName'].value;
            const firstNamePattern = /^([A-Za-z]{1,25}( [A-Za-z]{1,25}){0,24})?$/;
            if (firstName === "") {
                messages.firstName = 'First name should not be empty.';
                isValid = false;
                showError('firstName', messages.firstName);
            }
            if (!firstNamePattern.test(firstName)) {
                messages.firstName = 'First name should contain only alphabets and size should not exceed 25 characters.';
                isValid = false;
                showError('firstName', messages.firstName);
            }

            const lastName = form['lastName'].value;
            const lastNamePattern = /^([A-Za-z]{1,25}( [A-Za-z]{1,25}){0,24})?$/;
            if (lastName === "") {
                messages.lastName = 'Last name should not be empty.';
                isValid = false;
                showError('lastName', messages.lastName);
            }
            if (!lastNamePattern.test(lastName)) {
                messages.lastName = 'Last name should contain only alphabets and size should not exceed 25 characters.';
                isValid = false;
                showError('lastName', messages.lastName);
            }

            const email = form['email'].value;
            const emailPattern = /^[a-zA-Z0-9._%+-]{3,30}@gmail\.com$/;
            if (!emailPattern.test(email)) {
                messages.email = 'Invalid Email Id';
                isValid = false;
                showError('email', messages.email);
            }

            const phoneNumber = form['phoneNumber'].value;
            const phoneNumberPattern = /^[6-9]\d{9}$/;
            if (!phoneNumberPattern.test(phoneNumber)) {
                messages.phoneNumber = 'Phone Number can only start with 6,7,8,9 and should contain 10 digits';
                isValid = false;
                showError('phoneNumber', messages.phoneNumber);
            }

            const course = form['course'].value;
            if (course === "") {
                messages.course = 'Please select a course.';
                isValid = false;
                showError('course', messages.course);
            }

            const branch = form['branch'].value;
            if (branch === "") {
                messages.branch = 'Please select a branch.';
                isValid = false;
                showError('branch', messages.branch);
            }

            if (isValid) {
                // Prepare data for API request
                const profileData = {
                    firstName: form['firstName'].value,
                    lastName: form['lastName'].value,
                    email: form['email'].value,
                    phoneNumber: form['phoneNumber'].value,
                    course: form['course'].value,
                    branch: form['branch'].value,
                    updatedDate: new Date() // or any other date you want to send
                };

                try {
                    const response = await fetch(`${apiBaseUrl}student/updateStudent`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(profileData)
                    });
                    const result = await response.json();

                    if (response.ok) {
                        showResponseMessage(result.message || 'Profile updated successfully!', 'success');
                    } else {
                        showResponseMessage(result.message || 'Failed to update profile.', 'error');
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
                location.reload(); // Refresh the page
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
