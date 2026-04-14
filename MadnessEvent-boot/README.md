# MadnessEvent Spring Boot Application

A Spring Boot backend application for managing MadnessEvent events, DJs, merchandise products, and first ticket bookings.

## Project Structure

```text
MadnessEvent-boot/
|-- pom.xml                                      # Maven configuration and dependencies
|-- mvnw / mvnw.cmd                              # Maven wrapper scripts
|-- src/
|   |-- main/
|   |   |-- java/ch/fhnw/madnessevent/
|   |   |   |-- MadnessEventApplication.java     # Main Spring Boot entry point
|   |   |   |-- DataInitializer.java             # Sample data for testing the first services
|   |   |   |-- business/
|   |   |   |   |-- exception/                   # Custom exceptions for API errors
|   |   |   |   `-- service/                     # Business/service layer
|   |   |   |-- controller/                      # REST and dashboard controllers
|   |   |   |   `-- dto/                         # Request/response DTOs
|   |   |   `-- data/
|   |   |       |-- domain/                      # JPA entities
|   |   |       `-- repository/                  # Spring Data JPA repositories
|   |   `-- resources/
|   |       |-- application.properties           # Application, H2, and JPA configuration
|   |       |-- static/                          # CSS and JavaScript files
|   |       `-- templates/                       # Thymeleaf templates
|   `-- test/
|       `-- java/ch/fhnw/madnessevent/
|           `-- MadnessEventApplicationTests.java
`-- README.md
```

## Technologies

- Java 17
- Spring Boot 3.2.2
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Thymeleaf

## Implemented Milestone

For the milestone "Business and Service Layer, ORM & JPA", the first backend web services were implemented with Spring Boot, Spring Data JPA, and H2.

Implemented domain entities:

- `Event`
- `Dj`
- `Product`
- `TicketBooking`

Implemented layers:

- Domain/entity layer with JPA annotations
- Repository layer with Spring Data JPA
- Business service layer for validation and business logic
- REST controller layer for the first API endpoints

The application also initializes sample DJs, events, and products at startup, so the endpoints can be tested immediately after running the application.

## API Endpoints

- `GET /api/health` - Health check endpoint
- `GET /api/` - Welcome message
- `GET /api/events` - List all events
- `GET /api/events/{id}` - Get one event
- `POST /api/events` - Create an event
- `PUT /api/events/{id}` - Update an event
- `DELETE /api/events/{id}` - Delete an event
- `GET /api/djs` - List all DJs
- `GET /api/djs/{id}` - Get one DJ
- `POST /api/djs` - Create a DJ
- `PUT /api/djs/{id}` - Update a DJ
- `DELETE /api/djs/{id}` - Delete a DJ
- `GET /api/products` - List all merchandise products
- `GET /api/products/{id}` - Get one product
- `POST /api/products` - Create a product
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product
- `GET /api/tickets` - List ticket bookings
- `POST /api/tickets` - Book tickets for an event
- `GET /actuator/health` - Spring Boot Actuator health endpoint
- `GET /h2-console` - H2 Database Console for development

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+ or the included Maven wrapper

### Build

```powershell
cd MadnessEvent-boot
.\mvnw.cmd clean install
```

### Run

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

Example test URLs:

```text
http://localhost:8080/api/events
http://localhost:8080/api/djs
http://localhost:8080/api/products
http://localhost:8080/api/tickets
```

## Testing

Run unit tests:

```powershell
.\mvnw.cmd test
```

## Database

The application uses an H2 in-memory database by default. Access the H2 console at:

```text
http://localhost:8080/h2-console
```

Default credentials:

- JDBC URL: `jdbc:h2:mem:madnesseventdb`
- User: `sa`
- Password: empty

## Configuration

Edit `src/main/resources/application.properties` to customize:

- Server port
- Database settings
- JPA/Hibernate settings
- Logging levels
- Actuator endpoints

## Next Steps

1. Add cart and order checkout services.
2. Add e-ticket PDF download.
3. Configure API security and authentication.
4. Integrate the frontend with the backend endpoints.
