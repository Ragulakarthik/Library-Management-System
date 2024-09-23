document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');
    const sendOtpButton = document.getElementById('sendOtpButton');
    const otpSection = document.getElementById('otpSection');
    const otpInput = document.getElementById('otp');
    const responseMessage = document.getElementById('responseMessage');
    const apiBaseUrl = 'http://localhost:8080/api/'; // Ensure this is correct

    if (sendOtpButton) {
        sendOtpButton.addEventListener('click', async function() {
            let isValid = true;
            const messages = {
                firstName: '',
                lastName: '',
                email: '',
                phoneNumber: '',
                password: '',
                confirmPassword: '',
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

            const password = form['password'].value;
            const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.,])[A-Za-z\d@$!%*?&.,]{8,}$/;
            if (!passwordPattern.test(password)) {
                messages.password = 'Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character, and be at least 8 characters long.';
                isValid = false;
                showError('password', messages.password);
            }

            const pass = form['password'].value;
            const confirmPassword = form['confirm_password'].value;
            if (pass !== confirmPassword) {
                messages.confirmPassword = 'Passwords do not match';
                isValid = false;
                showError('confirm_password', messages.confirmPassword);
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
                const studentData = {
                    firstName: form['firstName'].value,
                    lastName: form['lastName'].value,
                    email: form['email'].value,
                    phoneNumber: form['phoneNumber'].value,
                    password: form['password'].value,
                    confirmPassword: form['confirm_password'].value,
                    course: form['course'].value,
                    branch: form['branch'].value,
                    registeredDate: new Date() // or any other date you want to send
                };

                try {
                    const response = await fetch(`${apiBaseUrl}student/registerStudent`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(studentData)
                    });
                    const result = await response.json();

                    if (response.ok) {
                        otpSection.style.display = 'block';
                        sendOtpButton.style.display = 'none';
                        showResponseMessage(result.message || 'OTP sent to the mail, Please check your mail');
                    } else {
                        showResponseMessage(result.message || 'Failed to register.', 'error');
                    }
                } catch (error) {
                    console.error('Error:', error);
                    showResponseMessage('An error occurred. Please try again later.', 'error');
                }
            }
        });
    }

    if (form) {
        form.addEventListener('submit', async function(event) {
            event.preventDefault();
            let isValid = true;
            const otp = otpInput.value;
            const otpPattern = /^\d{6}$/; // OTP should be exactly 6 digits

            const otpError = document.getElementById('otpError');
            if (otpError) {
                otpError.textContent = '';
            }

            if (!otpPattern.test(otp)) {
                isValid = false;
                otpError.textContent = 'OTP must be exactly 6 digits.';
            }

            if (isValid) {
                try {
                    const response = await fetch(`${apiBaseUrl}student/OTPVerify`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            email: form['email'].value,
                            otp: otp
                        })
                    });
                    const result = await response.json();

                    if (response.ok) {
                        showResponseMessage('Registration successful!', 'success');
                        resetForm(); // Call the function to reset the form
                    } else {
                        showResponseMessage(result.message || 'OTP verification failed.', 'error');
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

    function resetForm() {
        form.reset();
        otpSection.style.display = 'none';
        sendOtpButton.style.display = 'block';
        clearErrors();
    }
});
