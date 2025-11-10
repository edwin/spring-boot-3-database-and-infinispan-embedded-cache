# Spring Boot and Infinispan Cache

This project demonstrates the integration of Spring Boot with Infinispan for caching in a Java application. It showcases how to implement caching for a simple User management REST API using Infinispan's embedded mode.

## Technologies Used

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- H2 Database (in-memory)
- Infinispan 16.0.1 (embedded mode)
- Maven
- Lombok

## Project Overview

This application provides a REST API for managing user data with the following features:

- Create, retrieve users
- Automatic caching of user data using Infinispan
- Cache expiration settings (60 seconds lifespan, 30 seconds max idle time)
- Indexing for efficient querying of cached data

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/spring-boot-and-infinispan-cache.git
cd spring-boot-and-infinispan-cache
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## API Endpoints

### Welcome Endpoint

- **GET /** - Returns a simple welcome message
  ```json
  {
    "hello": "world"
  }
  ```

### User Management

- **GET /user** - Retrieve all users
- **GET /user/{username}** - Retrieve a specific user by username
- **POST /user** - Create a new user
  - Parameters:
    - name (String): User's name (primary key)
    - age (Integer): User's age
    - address (String): User's address
    - province (String): User's province

## Caching Configuration

The application uses Infinispan's embedded mode for caching with the following configuration:

- Cache name: "user-cache"
- Expiration settings:
  - Lifespan: 60 seconds
  - Max idle time: 30 seconds
  - Wake-up interval: 10 seconds
- Indexing enabled for User entities

## Database Configuration

The application uses an H2 in-memory database with the following configuration:

- URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password
- H2 Console: Enabled (accessible at /h2-console)

## Example Usage

### Create a User

```bash
curl -X POST "http://localhost:8080/user?name=john&age=30&address=123%20Main%20St&province=California"
```

### Get a User

```bash
curl -X GET "http://localhost:8080/user/john"
```

### Get All Users

```bash
curl -X GET "http://localhost:8080/user"
```

## How Caching Works

1. When a user is requested for the first time, it's retrieved from the database and stored in the cache.
2. Subsequent requests for the same user within the cache expiration period will be served from the cache without hitting the database.
3. When a user is updated, the cache is automatically updated.
4. The cache entries expire after 60 seconds of creation or after 30 seconds of inactivity.

## Author

Muhammad Edwin (edwin@redhat.com)
