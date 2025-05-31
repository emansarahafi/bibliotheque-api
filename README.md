# Bibliotheque API

This project is a simple RESTful API for managing a library's collection of books, built with Spring Boot, Spring Data JPA, and MySQL.

## Features

- CRUD operations for books (`Livre`)
- REST endpoints for listing, retrieving, creating, updating, and deleting books
- MySQL database integration
- Example endpoint for testing (`/hello`)

## Technologies Used

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Lombok

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL server running on `localhost:3306` with a database named `bibliotheque`

### Configuration

Database connection settings are in [`src/main/resources/application.properties`](src/main/resources/application.properties):
