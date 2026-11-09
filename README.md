# Employee Management API

REST API for employee management built with **Java 17**, **Spring Boot 2.7.x**, and **Hexagonal Architecture (Ports & Adapters)**.

## Overview

This project provides a RESTful API for managing employees and demonstrates the implementation of Hexagonal Architecture, Domain-Driven Design (DDD) concepts, centralized exception handling, API documentation, logging, and unit testing.

### Features

- Create employees
- Retrieve all employees
- Retrieve employee by ID
- Search employees by name
- Update employees
- Delete employees
- API documentation with Swagger/OpenAPI
- Centralized exception handling
- Validation with Bean Validation
- Unit testing with JUnit 5 and Mockito
- In-memory database using H2

---

# Technologies

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 2.7.x |
| Spring Data JPA | 2.7.x |
| Hibernate | Included |
| H2 Database | Runtime |
| Maven | 3.x |
| SpringDoc OpenAPI | Latest compatible |
| JUnit 5 | Testing |
| Mockito | Testing |
| SLF4J + Logback | Logging |

---

# Architecture

The project follows the **Hexagonal Architecture (Ports & Adapters)** pattern to keep business logic isolated from external frameworks and infrastructure concerns.

## Project Structure

```text
src/main/java
└── com
    └── pbo
        └── exampleemployee
            ├── application
            │   ├── employee
            │   │   ├── command
            │   │   ├── port
            │   │   │   ├── in
            │   │   │   └── out
            │   │   └── service
            │   └── exception
            │
            ├── domain
            │   ├── employee
            │   │   └── model
            │   │       └── vo
            │   └── exception
            │
            ├── infrastructure
            │   └── persistence
            │       └── employee
            │           ├── adapter
            │           ├── entity
            │           ├── mapper
            │           └── repository
            │
            └── interfaces
                └── rest
                    ├── common
                    │   ├── exception
                    │   └── response
                    │
                    └── employee
                        ├── controller
                        ├── http
                        │   ├── request
                        │   └── response
                        └── mapper
```

---

# Layer Responsibilities

## Domain Layer

Contains the core business rules and domain model.

### Responsibilities

- Employee domain model
- Value Objects (VO)
- Domain exceptions
- Business validations
- Framework-independent logic

---

## Application Layer

Contains application use cases and business orchestration.

### Command Objects

Used to transfer data to use cases.

Examples:

- `CreateEmployeeCommand`
- `UpdateEmployeeCommand`

### Ports In (Use Cases)

Define application entry points.

Examples:

- `CreateEmployeeUseCase`
- `GetEmployeeByIdUseCase`
- `GetAllEmployeesUseCase`
- `SearchEmployeesByNameUseCase`
- `UpdateEmployeeUseCase`
- `DeleteEmployeeUseCase`

### Ports Out

Define contracts for infrastructure dependencies.

Example:

```java
EmployeeRepositoryPort
```

### Services

Implement the use cases and coordinate application workflows.

---

## Infrastructure Layer

Contains technical implementations required by the application.

### Adapters

Implement outbound ports.

Example:

```java
EmployeePersistenceAdapter
```

### Entities

JPA entities used for persistence.

Example:

```java
EmployeeEntity
```

### Repositories

Spring Data JPA repositories.

Example:

```java
EmployeeJpaRepository
```

### Mappers

Convert between domain models and persistence entities.

```text
Employee ↔ EmployeeEntity
```

---

## Interfaces Layer

Responsible for exposing application functionality.

### Controllers

Expose REST endpoints.

Example:

```java
EmployeeController
```

### Request/Response DTOs

Handle HTTP communication.

Examples:

```java
CreateEmployeeRequest
UpdateEmployeeRequest
EmployeeResponse
```

### Mappers

Convert between HTTP DTOs and Application Commands.

### Common Components

Shared API infrastructure:

- GlobalExceptionHandler
- ErrorResponse
- Common API responses

---

# Employee Model

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "firstName": "Pablo",
  "middleName": "",
  "paternalLastName": "Rodriguez",
  "maternalLastName": "Ahuelican",
  "age": 30,
  "gender": "MALE",
  "active": true
}
```

---

# API Endpoints

## Create Employee

```http
POST /v1/employees
```

### Request

```json
{
  "firstName": "Pablo",
  "middleName": "",
  "paternalLastName": "Rodriguez",
  "maternalLastName": "Ahuelican",
  "age": 34,
  "gender": "MALE"
}
```

### Response

```http
201 Created
```

---

## Get All Employees

```http
GET /v1/employees
```

### Response

```http
200 OK
```

---

## Get Employee By ID

```http
GET /v1/employees/{id}
```

### Response

```http
200 OK
```

---

## Search Employees By Name

Supports partial matching.

```http
GET /v1/employees/search?name=john
```

### Response

```http
200 OK
```

---

## Update Employee

Supports complete or partial updates.

```http
PUT /v1/employees/{id}
```

### Request

```json
{
  "firstName": "Pablo Updated",
  "age": 35
}
```

### Response

```http
200 OK
```

---

## Delete Employee

```http
DELETE /v1/employees/{id}
```

### Response

```http
204 No Content
```

---

# API Documentation

After starting the application:

## Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI Specification

```text
http://localhost:8080/v3/api-docs

---

# Validation

The application uses Bean Validation.

Examples:

```java
@NotBlank
@NotNull
@Positive
@Min
@Max
```

Example:

```java
@NotBlank(message = "First name is required")
private String firstName;
```

---

# Exception Handling

Centralized exception handling is implemented using:

```java
@RestControllerAdvice

### Example Error Response

```json
{
  "timestamp": "2026-01-01T12:00:00",
  "status": 404,
  "message": "Employee not found",
  "path": "/v1/employees/550e8400-e29b-41d4-a716-446655440000"
}
```

---

# Logging

Logging is configured through `application.properties`.

```properties
logging.level.root=INFO
logging.level.com.pbo.exampleemployee=DEBUG

logging.file.name=logs/employee-management-api.log

logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
---

# Database

The project uses an in-memory H2 database for development and testing purposes.

## H2 Console

```text
http://localhost:8080/h2-console
```

## Configuration

```properties
spring.datasource.url=jdbc:h2:mem:employees
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

---

# Running the Application

## Clone Repository

```bash
https://github.com/PboAhuelican/employee-management-api.git
```

## Navigate to Project

```bash
cd employee-management-api
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

# Testing

Unit tests were implemented using:

- JUnit 5
- Mockito

## Execute Tests

```bash
mvn test
```

# Design Principles

- Hexagonal Architecture
- Ports and Adapters
- SOLID Principles
- Clean Code
- Separation of Concerns
- Dependency Inversion Principle
- Domain-Driven Design (DDD)
- Centralized Exception Handling
- OpenAPI Documentation

---

# Author

**Pablo Rodríguez Ahuelican**
