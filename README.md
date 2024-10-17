# **Library Management System**

**Introduction**
The Library Management System is a comprehensive tool designed to manage the operations of a library, including managing authors, publishers, books, and students. This system provides seamless CRUD (Create, Read, Update, Delete) operations, along with an efficient book issuing process. The system integrates OTP-based student registration and automates email notifications for issued books, including a 10-day deadline for returning the book.

Check out the Front Page : https://ragulakarthik.github.io/Library-Management-System/LMS-FRONEND/index.html
![image](https://github.com/user-attachments/assets/99f533c8-3227-410c-918b-631abaa6cf97)

![image](https://github.com/user-attachments/assets/7a6e74d2-f19f-4662-bc85-e54cd4870d97)

**Features**

**OTP-Based Registration:**

During student registration, an OTP (One-Time Password) is sent to the registered email for verification.
The registration completes only after the OTP is successfully verified.

**CRUD Operations:**

Perform Create, Read, Update, and Delete operations on the following entities:

**Authors**: Manage author information.

**Publishers**: Manage publisher details.

**Books**: Add, update, or delete book records.

**Students**: Manage student profiles, including their course and branch details.

**Book Issuing and Return:**

Books can be issued to students, and an email notification is sent, including details of the book and a 10-day deadline for returning it.
If the book is not returned within the deadline, a fine of ₹10 per day will be imposed on the student.
The student must clear the fine before being allowed to return the book.

**Email Notifications:**

Automated emails are sent when a book is issued, containing the following information:
Book details
Issuing date
Return deadline
Fine details (if applicable)

**Fine Calculation:**

If the book is returned late, the system calculates the fine based on the overdue days (₹10 per day).
The student must pay the fine before completing the return process.

**Technology Stack**:

**Backend**: Spring Boot

**Frontend**: HTML5, CSS3, JavaScript

**Database**: SQL (MySQL)

**OTP and Email Integration**: Java Mail API

**Validation**: Jakarta Bean Validation

****How It Works****

**Student Registration:**

The student fills out the registration form, providing essential details such as name, course, branch, and email.
Upon submission, an OTP is sent to the student’s email for verification.
Once the OTP is verified, the registration is successful.

**CRUD Operations:**

Admins can manage authors, publishers, books, and students by performing CRUD operations through a user-friendly interface.
Changes are reflected in the database in real-time.

**Issuing Books:**

Admins can issue a book to a student by selecting the student and book records.
An email is sent to the student with the book details and return deadline.

**Fine Calculation and Return:**

If a book is not returned within the 10-day window, a fine of ₹10 per day is calculated.
The student must pay the outstanding fine to proceed with returning the book.
Getting Started
Prerequisites
Java 11 or above
Spring Boot framework
PostgreSQL or MySQL database
Java Mail API for sending OTP and book issue notifications
Installation

**Clone the Repository:**

bash
Copy code
git clone https://github.com/Ragulakarthik/lms.git

**Set Up the Database:**

Create MySQL database.
Update the application.properties file with your database credentials:
properties
Copy code
spring.datasource.url=jdbc:mySql://localhost:3306/librarydb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword

**Run the Application:**

bash
Copy code
./mvnw spring-boot:run
Access the Application: Open your browser and go to http://localhost:8080 to access the Library Management System.

**Future Enhancements**:

Implementing SMS-based OTP in addition to email OTP.
Adding book reservation functionality.
Enhancing fine management with different fine rates for different types of books.
Generating detailed reports for overdue books, issued books, and fines collected.

**Contributing**:

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.

**Contact**
For any queries or issues, feel free to reach out:

**Email: ragulakarthik04@gmail.com**
