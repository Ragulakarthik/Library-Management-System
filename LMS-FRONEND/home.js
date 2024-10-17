// General function to handle actions
function handleAction(event, action) {
    event.preventDefault();
    
    if (action === 'showRegisterForm') {
        document.getElementById("registerFormSection").style.display = "block";
        window.location.href = "#registerFormSection";
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const ctx = document.getElementById('bookTransactionsChart').getContext('2d');
    const bookTransactionsChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            datasets: [
                {
                    label: 'Books Issued',
                    data: [30, 25, 40, 35, 50, 45, 55, 40, 60, 70, 65, 80],
                    backgroundColor: 'rgba(66, 165, 245, 0.7)',
                },
                {
                    label: 'Books Returned',
                    data: [25, 30, 35, 30, 45, 50, 60, 50, 70, 65, 70, 75],
                    backgroundColor: 'rgba(76, 175, 80, 0.7)',
                }
            ]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Number of Books'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Months'
                    }
                }
            }
        }
    });
});
