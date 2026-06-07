# Spring Boot CRUD Application

A RESTful CRUD application built with **Spring Boot**, **Spring Data JPA**, **Spring JDBC Template**, **MySQL**, and **Bean Validation**.

This project demonstrates:

* REST API development with Spring Boot
* CRUD operations using Spring Data JPA
* Input validation using Jakarta Validation
* Global exception handling
* MySQL database integration
* Spring JDBC Template for custom SQL queries
* Spring Bean Scopes (Singleton vs Prototype)

---

## Features

### User Management

* Create User
* Get All Users
* Get User By ID
* Update User
* Delete User

---

# API Quick Reference

**Base URL**

```http
http://localhost:8080
```

| Operation                | Method | Full Endpoint                                      |
| ------------------------ | ------ | -------------------------------------------------- |
| Create User              | POST   | `http://localhost:8080/api/users`                  |
| Get All Users            | GET    | `http://localhost:8080/api/users`                  |
| Get User By ID           | GET    | `http://localhost:8080/api/users/{id}`             |
| Update User              | PUT    | `http://localhost:8080/api/users/{id}`             |
| Delete User              | DELETE | `http://localhost:8080/api/users/{id}`             |
| Get Users Older Than Age | GET    | `http://localhost:8080/api/users/older-than/{age}` |
| Prototype Scope Demo     | GET    | `http://localhost:8080/api/users/prototype-demo`   |

---

# API Endpoints

## 1. Create User

Creates a new user in the database.

### Endpoint

```http
POST http://localhost:8080/api/users
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users" `
-Method Post `
-ContentType "application/json" `
-Body '{"name":"Alice","email":"alice@example.com","age":28}'
```

### Validation Rules

| Field | Rule                    |
| ----- | ----------------------- |
| name  | Minimum 2 characters    |
| email | Must be a valid email   |
| age   | Must be greater than 18 |

---

## 2. Get All Users

Retrieves all users from the MySQL database.

### Endpoint

```http
GET http://localhost:8080/api/users
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method Get
```

---

## 3. Get User By ID

Retrieves a specific user by ID.

### Endpoint

```http
GET http://localhost:8080/api/users/{id}
```

### Example

```http
GET http://localhost:8080/api/users/1
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users/1" -Method Get
```

### Error Handling

If the user does not exist, a `ResourceNotFoundException` is thrown and handled by the Global Exception Handler, returning a clean JSON response with HTTP 404.

---

## 4. Update User

Updates an existing user's details.

### Endpoint

```http
PUT http://localhost:8080/api/users/{id}
```

### Example

```http
PUT http://localhost:8080/api/users/1
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users/1" `
-Method Put `
-ContentType "application/json" `
-Body '{"name":"Alice Updated","email":"alice.new@example.com","age":29}'
```

---

## 5. Delete User

Deletes a user from the database.

### Endpoint

```http
DELETE http://localhost:8080/api/users/{id}
```

### Example

```http
DELETE http://localhost:8080/api/users/1
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users/1" -Method Delete
```

---

## 6. Spring JDBC Template Demo

Executes a custom SQL query using Spring JDBC Template instead of Spring Data JPA.

### Endpoint

```http
GET http://localhost:8080/api/users/older-than/{age}
```

### Example

```http
GET http://localhost:8080/api/users/older-than/20
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users/older-than/20" -Method Get
```

---

## 7. Prototype Scope Demo

Demonstrates Spring's Prototype Scope.

Each request returns a different UUID, proving that a new bean instance is created every time the endpoint is called.

### Endpoint

```http
GET http://localhost:8080/api/users/prototype-demo
```

### Example Request

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users/prototype-demo" -Method Get
```

---

# Project Structure

```text
src
├── controller
├── service
├── repository
├── dto
├── exception
├── config
├── scope
└── model
```

---

# Exception Handling

The application uses a centralized Global Exception Handler to handle:

* Validation Errors
* Resource Not Found Exceptions
* Generic Exceptions

This ensures consistent and meaningful API responses.

---

# Validation

Validation is performed using Jakarta Bean Validation annotations such as:

```java
@NotBlank
@Email
@Min
@Size
```

Requests with invalid data automatically return validation errors.

---

# Running the Project

## Clone Repository

```bash
git clone <repository-url>
```

## Navigate to Project

```bash
cd project-name
```

## Configure MySQL

Update your database credentials in:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password
```

## Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```http
http://localhost:8080
```

---

# Learning Concepts Demonstrated

* Spring Boot REST APIs
* Dependency Injection
* Spring Data JPA
* CRUD Operations
* Bean Validation
* Exception Handling
* Spring JDBC Template
* MySQL Integration
* Bean Scopes
* Maven Project Structure

---
