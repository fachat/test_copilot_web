# Quarkus Web Application Skeleton

This is a skeleton Java-based web application built with **Quarkus** and **JakartaEE** that demonstrates:

- **RESTful API Services** - JAX-RS for CRUD operations
- **PostgreSQL Database Integration** - JPA/Hibernate with Flyway migrations
- **OAuth 2.0/OIDC Authentication** - OpenID Connect for authentication
- **Server-Side Rendering** - Qute template engine for dynamic HTML pages
- **Database Migrations** - Flyway for schema version management
- **Container-Ready** - Multi-stage Dockerfile for minimal image size
- **Kubernetes-Ready** - Init containers for database initialization

## Technology Stack

- Java 17
- Quarkus 3.6.4
- JakartaEE (JAX-RS, CDI, JPA, Bean Validation)
- Hibernate ORM with Panache
- Flyway (database migrations)
- Qute (templating)
- PostgreSQL
- Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+ (running on localhost:5432)
- Docker (optional, for containerized deployment)
- Kubernetes/kubectl (optional, for K8s deployment)

## Database Setup

1. Install and start PostgreSQL
2. Create a database:
   ```sql
   CREATE DATABASE webapp_db;
   CREATE USER webapp_user WITH PASSWORD 'webapp_password';
   GRANT ALL PRIVILEGES ON DATABASE webapp_db TO webapp_user;
   ```

Flyway will automatically run migrations when the application starts.

## OAuth/OIDC Setup (Optional)

1. Configure your OIDC provider (GitHub, Google, etc.)
2. Set environment variables:
   ```bash
   export QUARKUS_OIDC_CLIENT_ID=YOUR_CLIENT_ID
   export QUARKUS_OIDC_CREDENTIALS_SECRET=YOUR_CLIENT_SECRET
   ```

## Running the Application

### Development Mode

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run in development mode (with live reload):
   ```bash
   mvn quarkus:dev
   ```

3. Access the application at `http://localhost:8080`

### Production Mode

1. Build the application:
   ```bash
   mvn clean package
   ```

2. Run the packaged application:
   ```bash
   java -jar target/quarkus-app/quarkus-run.jar
   ```

### Docker

Build and run using Docker with multi-stage build:

```bash
# Build the Docker image
docker build -t webapp-skeleton:latest .

# Run the container
docker run -p 8080:8080 \
  -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://host.docker.internal:5432/webapp_db \
  webapp-skeleton:latest
```

### Kubernetes Deployment

Deploy to Kubernetes with init container for Flyway migrations:

```bash
# Apply PostgreSQL deployment
kubectl apply -f k8s/postgres.yaml

# Apply application deployment with init container
kubectl apply -f k8s/deployment.yaml

# Access the application
kubectl port-forward svc/webapp-service 8080:8080
```

The init container will automatically run Flyway migrations before the application starts.

## API Endpoints

### REST API

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
  ```json
  {
    "username": "john_doe",
    "email": "john@example.com"
  }
  ```
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Health Checks

- `GET /q/health` - Application health check
- `GET /q/health/live` - Liveness probe
- `GET /q/health/ready` - Readiness probe

### Web Pages

- `/` - Public home page
- `/home` - Application home page

## Testing the REST API

You can test the API using curl:

```bash
# Create a user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username": "john_doe", "email": "john@example.com"}'

# Get all users
curl http://localhost:8080/api/users

# Get user by ID
curl http://localhost:8080/api/users/1

# Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"username": "jane_doe", "email": "jane@example.com"}'

# Delete user
curl -X DELETE http://localhost:8080/api/users/1
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/webapp/
│   │   ├── WebAppApplication.java       # Main Quarkus application class
│   │   ├── controller/
│   │   │   ├── UserRestController.java  # JAX-RS REST endpoints
│   │   │   └── WebController.java       # Web page controllers
│   │   ├── model/
│   │   │   └── User.java                # JPA entity
│   │   ├── repository/
│   │   │   └── UserRepository.java      # Panache repository
│   │   └── service/
│   │       └── UserService.java         # Business logic (CDI)
│   └── resources/
│       ├── application.properties       # Quarkus configuration
│       ├── db/migration/                # Flyway migrations
│       │   └── V1__Create_users_table.sql
│       ├── static/
│       │   └── css/
│       │       └── style.css           # Stylesheet
│       └── templates/                   # Qute templates
│           ├── index.html
│           └── home.html
├── test/
│   └── java/com/example/webapp/
│       └── WebAppApplicationTests.java  # Quarkus tests
└── k8s/
    ├── deployment.yaml                  # K8s deployment with init container
    └── postgres.yaml                    # PostgreSQL deployment

Dockerfile                               # Multi-stage Docker build
```

## Features Demonstrated

### 1. JakartaEE Standards
- **JAX-RS** for RESTful API endpoints
- **CDI** (Contexts and Dependency Injection) for dependency injection
- **JPA** (Java Persistence API) with Hibernate ORM
- **Bean Validation** for input validation
- Full CRUD operations with RESTful conventions
- JSON request/response handling with JSON-B
- HTTP status codes (200, 201, 204, 404)

### 2. PostgreSQL Database Integration
- JPA/Hibernate ORM with Panache
- Entity relationships and lifecycle callbacks
- Repository pattern with Panache
- Automated schema management with Flyway

### 3. Flyway Database Migrations
- Version-controlled schema changes
- Automatic migration on startup
- Init container pattern for Kubernetes
- Baseline on migrate for existing databases

### 4. Server-Side Rendering
- Qute template engine
- Dynamic HTML generation
- Type-safe templates
- Fast rendering performance

### 5. Container and Kubernetes Ready
- Multi-stage Dockerfile for minimal image size
- Init container for database migrations
- Health checks (liveness and readiness probes)
- ConfigMaps and Secrets for configuration
- Kubernetes deployment manifests

## Running Tests

Run the test suite:

```bash
mvn test
```

The tests use H2 in-memory database and don't require PostgreSQL.

## Troubleshooting

### Database Connection Issues
- Ensure PostgreSQL is running
- Verify database credentials in `application.properties`
- Check if the database exists
- Flyway will create tables automatically

### Build Issues
- Ensure Java 17 is installed: `java -version`
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`

### Docker Issues
- Ensure Docker daemon is running
- Check database connectivity from container
- Use `host.docker.internal` for database URL on Docker Desktop

## License

This project is a skeleton/template for learning and development purposes.

## License

This is a skeleton project for educational purposes.