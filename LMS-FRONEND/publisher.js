// Base API URL for the publisher endpoints
const baseUrl = 'http://localhost:8080/api/publisher';

// Function to display publishers in the table
function displayPublishers(data) {
    const tbody = document.querySelector("#publishers-table tbody");
    tbody.innerHTML = ""; // Clear previous entries

    data.forEach(publisher => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${publisher.publisherId}</td>
            <td>${publisher.publisherName}</td>
            <td>${publisher.phoneNumber}</td>
            <td>
                <button class="update-btn" onclick="updatePublisher(${publisher.publisherId})">Update</button>
                <button class="delete-btn" onclick="deletePublisher(${publisher.publisherId})">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// Function to handle the "Get" button click
document.getElementById("get-data-btn").addEventListener("click", () => {
    const id = document.getElementById("search-id").value.trim();
    const name = document.getElementById("search-name").value.trim().toLowerCase();

    // Build the search query parameters based on the input
    let url = `${baseUrl}/getPublishersByParam?`;
    if (id) {
        url += `publisherId=${id}&`;
    }
    if (name) {
        url += `publisherName=${encodeURIComponent(name)}&`;
    }

    // Remove the trailing '&' or '?' if no parameters were added
    url = url.slice(0, -1);

    // Fetch the publishers from the backend API
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Display publishers in the table
            displayPublishers(data);
        })
        .catch(error => console.error('Error fetching publishers:', error));
});

// Function to handle publisher update (dummy functionality)
function updatePublisher(id) {
    alert(`Update publisher with ID: ${id}`);
    // Implement the actual update logic here
}

// Function to delete a publisher
function deletePublisher(id) {
    // Confirm before deleting the publisher
    const confirmation = confirm(`Are you sure you want to delete the publisher with ID: ${id}?`);
    
    if (confirmation) {
        // API URL to delete the publisher
        const url = `${baseUrl}/deletePublisher?publisherId=${id}`;

        // Send DELETE request to the server
        fetch(url, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert(`Publisher with ID: ${id} has been deleted.`);
                // Remove the deleted publisher from the table
                removePublisherFromTable(id);
            } else {
                alert('Failed to delete the publisher. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error deleting publisher:', error);
            alert('An error occurred while trying to delete the publisher.');
        });
    }
}

// Function to remove the deleted publisher from the table
function removePublisherFromTable(id) {
    const rows = document.querySelectorAll("#publishers-table tbody tr");

    rows.forEach(row => {
        const firstCell = row.querySelector("td:first-child");
        if (firstCell && firstCell.textContent == id) {
            row.remove(); // Remove the matching row
        }
    });
}
