# 📚 Library Management System

The **Library Management System** is a comprehensive tool for managing library operations, including managing authors, publishers, books, and students. It provides seamless CRUD (Create, Read, Update, Delete) operations and an efficient book issuing process, along with OTP-based student registration and automated email notifications.

## 🔗 Links
- **Backend Code**: [GitLab Repo](https://gitlab.com/Ragula_Karthik/lms)
- **Live Frontend Demo**: [Live Demo](https://ragulakarthik.github.io/Library-Management-System/LMS-FRONEND/index.html)

---

## 🚀 Features

- **OTP-Based Registration**: Students are required to verify their registration with an OTP sent via email.
- **CRUD Operations**: Manage authors, publishers, books, and students.
- **Book Issuing & Return**: Issue books to students and manage return deadlines with automatic fines.
- **Email Notifications**: Automated emails for issued books and overdue reminders.
- **Fine Calculation**: Overdue books incur a ₹10/day fine.

---

## 🛠️ Technology Stack

- **Backend**: Spring Boot
- **Frontend**: HTML5, CSS3, JavaScript
- **Database**: MySQL
- **OTP & Email Integration**: Java Mail API
- **Validation**: Jakarta Bean Validation

---

## 📝 How It Works

### 👨‍🎓 Student Registration

1. The student fills out a registration form.
2. An OTP is sent to the student's email.
3. Registration is completed only after successful OTP verification.

### 🔄 CRUD Operations

Admins can manage authors, publishers, books, and students through an easy-to-use interface. All changes are reflected in real-time.

### 📤 Issuing Books

Admins can select a student and a book to issue. The system will send an email to the student, detailing the book's return deadline.

### 💵 Fine Calculation

A ₹10 per day fine is applied for late returns. The fine must be paid before the return process can be completed.

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 11 or above
- Spring Boot
- MySQL
- Java Mail API for sending OTP and email notifications

### 🔧 Installation

1. **Clone the repository**:
    ```bash
    git clone https://gitlab.com/Ragula_Karthik/lms.git
    ```

2. **Set up the database**:
    - Create a MySQL database.
    - Update the `application.properties` file with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application**:
    Open your browser and go to `http://localhost:8080` to access the Library Management System.

---

## 🌟 Future Enhancements

- Implement SMS-based OTP in addition to email verification.
- Add a book reservation feature.
- Introduce variable fine rates for different categories of books.
- Generate detailed reports for issued books, overdue fines, and book availability.

---

## 🤝 Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any proposed changes.

---

## 📧 Contact

For any queries or support, please reach out to:

**Email**: ragulakarthik04@gmail.com
