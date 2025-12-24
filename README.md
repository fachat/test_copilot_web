# Java Web Application Skeleton

This is a skeleton Java-based web application built with Spring Boot that demonstrates:

- **RESTful API Services** - CRUD operations for data access
- **PostgreSQL Database Integration** - JPA/Hibernate for data persistence
- **OAuth 2.0 Authentication** - Authorization code flow with GitHub as identity provider
- **Server-Side Rendering** - Thymeleaf template engine for dynamic HTML pages
- **Security** - Spring Security for authentication and authorization

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security with OAuth2
- Thymeleaf
- PostgreSQL
- Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+ (running on localhost:5432)
- GitHub OAuth App credentials (for authentication)

## Database Setup

1. Install and start PostgreSQL
2. Create a database:
   ```sql
   CREATE DATABASE webapp_db;
   CREATE USER webapp_user WITH PASSWORD 'webapp_password';
   GRANT ALL PRIVILEGES ON DATABASE webapp_db TO webapp_user;
   ```

## OAuth Setup

1. Go to GitHub Settings > Developer settings > OAuth Apps
2. Click "New OAuth App"
3. Fill in the details:
   - Application name: Your app name
   - Homepage URL: `http://localhost:8080`
   - Authorization callback URL: `http://localhost:8080/login/oauth2/code/github`
4. Copy the Client ID and Client Secret
5. Create `src/main/resources/application-local.properties` with:
   ```properties
   spring.security.oauth2.client.registration.github.client-id=YOUR_CLIENT_ID
   spring.security.oauth2.client.registration.github.client-secret=YOUR_CLIENT_SECRET
   ```

## Running the Application

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Access the application at `http://localhost:8080`

## API Endpoints

### REST API (requires authentication)

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

### Web Pages

- `/` - Public home page
- `/login` - Login page
- `/home` - Authenticated home page with user profile
- `/users` - User management page

## Testing the REST API

After authentication, you can test the API using curl:

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
│   │   ├── WebAppApplication.java       # Main application class
│   │   ├── config/
│   │   │   └── SecurityConfig.java      # OAuth2 security configuration
│   │   ├── controller/
│   │   │   ├── UserRestController.java  # REST API endpoints
│   │   │   └── WebController.java       # Web page controllers
│   │   ├── model/
│   │   │   └── User.java                # JPA entity
│   │   ├── repository/
│   │   │   └── UserRepository.java      # Data access layer
│   │   └── service/
│   │       └── UserService.java         # Business logic
│   └── resources/
│       ├── application.properties       # Application configuration
│       ├── static/
│       │   └── css/
│       │       └── style.css           # Stylesheet
│       └── templates/                   # Thymeleaf templates
│           ├── index.html
│           ├── login.html
│           ├── home.html
│           └── users.html
└── test/
    └── java/com/example/webapp/
        └── WebAppApplicationTests.java  # Integration tests
```

## Features Demonstrated

### 1. RESTful API Services
- Full CRUD operations with RESTful conventions
- JSON request/response handling
- HTTP status codes (200, 201, 204, 404)
- Request validation with Bean Validation

### 2. PostgreSQL Database Integration
- JPA/Hibernate ORM
- Entity relationships and lifecycle callbacks
- Spring Data JPA repositories
- Automatic schema generation

### 3. OAuth 2.0 Authorization Code Flow
- GitHub as identity provider
- Secure token-based authentication
- User profile information retrieval
- Session management

### 4. Server-Side Rendering
- Thymeleaf template engine
- Dynamic HTML generation
- Template fragments and layouts
- Model-View-Controller pattern

### 5. Security
- Protected API endpoints
- Public and authenticated routes
- OAuth2 login/logout flows
- CSRF protection

## Troubleshooting

### Database Connection Issues
- Ensure PostgreSQL is running
- Verify database credentials in `application.properties`
- Check if the database exists

### OAuth Authentication Issues
- Verify GitHub OAuth credentials
- Ensure callback URL matches GitHub app settings
- Check `application-local.properties` configuration

### Build Issues
- Ensure Java 17 is installed: `java -version`
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`

## License

This is a skeleton project for educational purposes.