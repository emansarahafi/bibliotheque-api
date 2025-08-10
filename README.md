# Bibliotheque API

This project is a comprehensive RESTful API for managing a library system, built with Spring Boot, Spring Data JPA, and MySQL. It includes complete OpenAPI documentation with Swagger UI for easy testing and exploration.

## Features

### Core Functionality

- **Books Management (`Livre`)**: Full CRUD operations for library books
- **Loans Management (`Emprunt`)**: Complete loan tracking system
- **RESTful endpoints** with proper HTTP status codes and error handling
- **MySQL database integration** with JPA/Hibernate
- **Data validation** with Bean Validation annotations

### Documentation Features

- **OpenAPI 3.0 specification** with comprehensive documentation
- **Swagger UI** interface for interactive API testing
- **Multi-group API organization** (Books and Loans)
- **Detailed endpoint descriptions** with examples
- **Schema documentation** for all data models

### Quality Features

- **Global exception handling** with custom error responses
- **Business logic validation** with custom exceptions
- **Organized code structure** with DTOs, services, and repositories
- **Lombok integration** for reduced boilerplate code

## Technologies Used

- **Java 21**
- **Spring Boot 3.2.1** (stable version for compatibility)
- **Spring Data JPA** for database operations
- **SpringDoc OpenAPI 2.2.0** for API documentation
- **MySQL 8.0** database
- **Lombok** for code generation
- **Maven** for dependency management

## OpenAPI Documentation

The API is fully documented using OpenAPI 3.0 and includes:

- **Interactive Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Books API Documentation**: [http://localhost:8080/v3/api-docs/livres](http://localhost:8080/v3/api-docs/livres)
- **Loans API Documentation**: [http://localhost:8080/v3/api-docs/emprunts](http://localhost:8080/v3/api-docs/emprunts)

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.6+
- MySQL server running on `localhost:3306` with a database named `bibliotheque`

### Database Setup

Create a MySQL database:

```sql
CREATE DATABASE bibliotheque;
```

Optionally, create a dedicated user:

```sql
CREATE USER 'bibliotheque_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON bibliotheque.* TO 'bibliotheque_user'@'localhost';
```

### Configuration

Database and API documentation settings are in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

# OpenAPI Documentation Configuration
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.operationsSorter=alpha
springdoc.swagger-ui.urls[0].name=Gestion des Livres
springdoc.swagger-ui.urls[0].url=/v3/api-docs/livres
springdoc.swagger-ui.urls[1].name=Gestion des Emprunts
springdoc.swagger-ui.urls[1].url=/v3/api-docs/emprunts
```

### Running the Application

Clone the repository:

```bash
git clone <repository-url>
cd bibliotheque-api
```

Build and run with Maven:

```bash
mvn clean install
mvn spring-boot:run
```

Access the application:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Health Check**: [http://localhost:8080/hello](http://localhost:8080/hello)

## API Endpoints

### Books (Livres) - `/api/livres`

- `GET /api/livres` - List all books
- `GET /api/livres/{id}` - Get book by ID
- `POST /api/livres` - Create a new book
- `PUT /api/livres/{id}` - Update an existing book
- `DELETE /api/livres/{id}` - Delete a book

### Loans (Emprunts) - `/api/emprunts`

- `GET /api/emprunts` - List all loans
- `GET /api/emprunts/{id}` - Get loan by ID
- `POST /api/emprunts` - Create a new loan
- `PUT /api/emprunts/{id}` - Update an existing loan
- `DELETE /api/emprunts/{id}` - Delete a loan

### Utility

- `GET /hello` - Simple health check endpoint

## Data Models

### Book (Livre)

```json
{
  "id": 1,
  "titre": "Example Book Title",
  "auteur": "Author Name",
  "isbn": "978-0123456789",
  "disponible": true
}
```

### Loan (Emprunt)

```json
{
  "id": 1,
  "livreId": 1,
  "nomEmprunteur": "John Doe",
  "dateEmprunt": "2025-08-10",
  "dateRetourPrevue": "2025-08-24",
  "dateRetourReel": null,
  "statut": "EN_COURS"
}
```

## Error Handling

The API includes comprehensive error handling with appropriate HTTP status codes:

- `400 Bad Request` - Invalid request data
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server errors

Error responses follow this format:

```json
{
  "timestamp": "2025-08-10T21:54:25.338447",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/livres"
}
```

## Development

### Project Structure

```text
src/
├── main/
│   ├── java/com/example/bibliotheque/
│   │   ├── config/           # Configuration classes
│   │   ├── controller/       # REST controllers
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── exception/        # Exception handling
│   │   ├── model/            # Entity models
│   │   ├── repository/       # Data repositories
│   │   └── service/          # Business logic
│   └── resources/
│       └── application.properties
└── test/                     # Unit tests
```

### Testing

Run tests with Maven:

```bash
mvn test
```

Access the interactive API documentation at Swagger UI to test endpoints manually.

## Troubleshooting

### Common Issues

1. **Connection refused error**: Ensure MySQL is running and the database exists
2. **Version compatibility issues**: This project uses Spring Boot 3.2.1 with SpringDoc OpenAPI 2.2.0 for stability
3. **Port already in use**: Check if another application is running on port 8080

### Logs

Application logs are available in:

- Console output when running with `mvn spring-boot:run`
- Log files: `app.log` and `app_new.log`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if necessary
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
