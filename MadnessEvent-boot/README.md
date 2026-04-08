# MadnessEvent Spring Boot Application

A Spring Boot application for managing madness events.

## Project Structure

```
MadnessEvent-boot/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/ch/fhnw/madnessevent/
│   │   │   ├── MadnessEventApplication.java      # Main Spring Boot entry point
│   │   │   └── controller/
│   │   │       └── HealthController.java         # REST API endpoints
│   │   └── resources/
│   │       └── application.properties            # Application configuration
│   └── test/
│       └── java/ch/fhnw/madnessevent/
│           └── MadnessEventApplicationTests.java # Unit tests
└── .gitignore
```

## Technologies

- **Java 17**
- **Spring Boot 3.2.2**
- **Maven**
- **H2 Database**
- **Spring Data JPA**
- **Project Lombok**

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build

```bash
cd MadnessEvent-boot
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

## Testing

Run unit tests:

```bash
mvn test
```

## API Endpoints

- `GET /api/health` - Health check endpoint
- `GET /api/` - Welcome message
- `GET /actuator/health` - Spring Boot Actuator health endpoint
- `GET /h2-console` - H2 Database Console (development only)

## Configuration

Edit `src/main/resources/application.properties` to customize:
- Server port
- Database settings
- Logging levels
- Actuator endpoints

## Development

### Enable DevTools

DevTools is included in the project for faster development with automatic restart on file changes.

### IDE Setup

Import the project as a Maven project in your IDE (VS Code, IntelliJ, Eclipse, etc.)

## Database

The application uses H2 in-memory database by default. Access the H2 console at:
```
http://localhost:8080/h2-console
```

Default credentials:
- JDBC URL: `jdbc:h2:mem:madnesseventdb`
- User: `sa`
- Password: (empty)

## Next Steps

1. Add more domain entities and repositories
2. Create business logic services
3. Implement more REST endpoints
4. Add validation and error handling
5. Configure security if needed
