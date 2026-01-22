# Todo API

A RESTful API for managing todo tasks built with Spring Boot and PostgreSQL.

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.x
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito

## Features

- ✅ Full CRUD operations for todos
- ✅ Input validation
- ✅ Global exception handling
- ✅ Pagination and sorting
- ✅ Search functionality
- ✅ Comprehensive unit and integration tests
- 🔄 API documentation with Swagger (in progress)
- 🔄 AI-powered suggestions (in progress)

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/todos` | Get all todos (paginated) |
| GET | `/api/todos/{id}` | Get todo by ID |
| POST | `/api/todos` | Create new todo |
| PUT | `/api/todos/{id}` | Update todo |
| DELETE | `/api/todos/{id}` | Delete todo |
| GET | `/api/todos/search?query={query}` | Search todos |

## Setup Instructions

### Prerequisites
- Java 17
- PostgreSQL
- Maven

### Installation

1. Clone the repository
```bash
git clone <your-repo-url>
cd todo-api
```

2. Configure database in `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tododb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## Running Tests
```bash
mvn test
```

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/yourpackage/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── model/
│   │       └── exception/
│   └── resources/
│       └── application.properties
└── test/
```

## Future Enhancements

- AI-powered task suggestions
- User authentication
- Task categories and tags
- Due date reminders