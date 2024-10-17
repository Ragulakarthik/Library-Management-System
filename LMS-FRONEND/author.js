// Base API URL
const baseUrl = 'http://localhost:8080/api/author/getAuthorsByParam';

// Function to display authors in the table
function displayAuthors(data) {
    const tbody = document.querySelector("#authors-table tbody");
    tbody.innerHTML = ""; // Clear previous entries

    data.forEach(author => {
        // Adjust key names based on the API response structure
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${author.authorId}</td>
            <td>${author.authorName}</td>
            <td>${author.phoneNumber}</td>
            <td>
                <button class="update-btn" onclick="updateAuthor(${author.authorId || author.id})">Update</button>
                <button class="delete-btn" onclick="deleteAuthor(${author.authorId || author.id})">Delete</button>
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
    let url = `${baseUrl}?`;
    if (id) {
        url += `authorId=${id}&`;
    }
    if (name) {
        url += `authorName=${encodeURIComponent(name)}&`;
    }

    // Remove the trailing '&' or '?' if no parameters were added
    url = url.slice(0, -1);

    // Fetch the authors from the backend API
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Display authors in the table
            displayAuthors(data);
        })
        .catch(error => console.error('Error fetching authors:', error));
});

// Function to handle author update (dummy functionality)
function updateAuthor(id) {
    alert(`Update author with ID: ${id}`);
    // Implement the actual update logic here
}

// Function to delete an author
function deleteAuthor(id) {
    // Confirm before deleting the author
    const confirmation = confirm(`Are you sure you want to delete the author with ID: ${id}?`);
    
    if (confirmation) {
        // API URL to delete the author
        const url = `http://localhost:8080/api/author/deleteAuthor?authorId=${id}`;

        // Send DELETE request to the server
        fetch(url, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert(`Author with ID: ${id} has been deleted.`);
                // Remove the deleted author from the table
                removeAuthorFromTable(id);
            } else {
                alert('Failed to delete the author. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error deleting author:', error);
            alert('An error occurred while trying to delete the author.');
        });
    }
}

// Function to remove the deleted author from the table
function removeAuthorFromTable(id) {
    const rows = document.querySelectorAll("#authors-table tbody tr");

    rows.forEach(row => {
        const firstCell = row.querySelector("td:first-child");
        if (firstCell && firstCell.textContent == id) {
            row.remove(); // Remove the matching row
        }
    });
}