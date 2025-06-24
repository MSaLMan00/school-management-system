# 🏫 School Management System - Spring Boot Project

A complete RESTful API built using **Spring Boot**, **Spring Data JPA**, and **MySQL** that manages students, courses, and grades.

---

## 📦 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **MySQL 8+**
- **Hibernate**
- **Lombok**
- **HikariCP** (Connection Pool)
- **Springdoc OpenAPI (Swagger UI)**

---

## 🛠️ Setup Instructions

### ✅ Prerequisites

- Java 17 installed
- MySQL Server running on `localhost:3306`
- Maven installed
- Postman or any API testing tool

---

### 🔧 Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE school_db;
Update your credentials in application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3307/school_sys
spring.datasource.username=root
spring.datasource.password=root
🚀 Run the Project
bash
Copy
Edit
mvn spring-boot:run
Swagger UI:
http://localhost:8080/swagger-ui.html

📖 API Endpoints Summary
Base URL: http://localhost:8080

📚 Students
Method	Endpoint	Description
POST	/api/students	Create a student
GET	/api/students	Get all students
GET	/api/students/{id}	Get student by ID
PUT	/api/students/{id}	Update student
DELETE	/api/students/{id}	Delete student

📘 Courses
Method	Endpoint	Description
POST	/api/courses	Create a course
GET	/api/courses	Get all courses
GET	/api/courses/{id}	Get course by ID
PUT	/api/courses/{id}	Update course
DELETE	/api/courses/{id}	Delete course

📝 Grades
Method	Endpoint	Description
POST	/api/grades	Assign grade
GET	/api/grades/student/{studentId}	Get grades by student ID
GET	/api/grades/student/{studentId}/course/{courseId}	Get grade by student & course

⚙️ Swagger UI
Once the app is running, access full API docs at:

http://localhost:8080/swagger-ui.html

💾 Sample application.properties
properties
Copy
Edit
spring.application.name=school_management

spring.datasource.url=jdbc:mysql://localhost:3306/school_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.pool-name=SchoolHikariCP

springdoc.swagger-ui.path=/swagger-ui.html
📂 Project Structure
matlab
Copy
Edit
school_management/
├── CONTROLLERS/
├── SERVICES/
├── REPOSITORIES/
├── ENTITIES/
├── DTOS/
└── application.properties
👤 Author
Your Name
M SALMAN KHAN
https://www.linkedin.com/in/muhammad-salman-khan-94011a1ba/
