// General function to handle actions
function handleAction(event, action) {
    event.preventDefault();
    
    if (action === 'showRegisterForm') {
        document.getElementById("registerFormSection").style.display = "block";
        window.location.href = "#registerFormSection";
    }
}