# Pole metrics API

## Description
This Pole metrics API is a Spring Boot application that provides endpoints to manage charge pole locations and meter values. It also offers operations to generate charging reports.
## Getting Started
To get a copy of this project up and running on your local machine, follow these steps:

### Prerequisites
- Java Development Kit (JDK) version 17 or higher
- Maven
- Docker

### Database Configuration
The application runs on a PostgreSQL database. The database configuration is specified in application.properties. For testing, Testcontainers is used to spin up a PostgreSQL container with a separate test database.

### Installation
1. Clone the repository:
````text
git clone <repository-url>
````
2. Navigate to the project directory:
````text
cd pole-metrics
````
3. Build the project using Maven:
````text
mvn clean install
````
4. Run the application:
````text
mvn spring-boot:run
````

### Usage
Once the application is running, you can access the endpoint via Swagger-UI 
- `http://localhost:8080/swagger-ui/index.html`

### Endpoint
- GET /api/v1/reports: Retrieves all locations reports.
- GET /api/v1/meter-values: Retrieves all meter values.
- GET /api/v1/locations: Retrieves all locations.
- GET /api/v1/fetch-data: Retrieves all pole data and save in database.


### Testing
This project includes unit and integration tests to ensure the correctness of the functionality. You can run the tests using Maven:
````text
mvn test
````

### Contact
For any questions or concerns, please contact Lars at alimjan.ablikim@gmail.com



