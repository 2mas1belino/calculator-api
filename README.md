# Calculator REST API

A Spring Boot-based RESTful calculator API with Kafka integration, developed for the WIT technical challenge.

## Features

- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Support for arbitrary precision decimal numbers using BigDecimal
- Asynchronous processing using Apache Kafka
- Multi-module Maven project
- Docker support

## Modules

1. **api-common**: Shared components
2. **api-dtos**: Data Transfer Objects
3. **calculator**: Core calculation logic
4. **rest**: REST API endpoints

## Prerequisites

- Java 17
- Maven 3.8+
- Docker (for Kafka and containerized deployment)
- Apache Kafka (can be run via Docker)

## Technologies

- Spring Boot 3.5.3
- Apache Kafka
- Maven
- JUnit 5
- Docker

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/calculator-api.git
cd calculator-api
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Start Kafka with Docker

```bash
docker-compose up -d 
```

## API Endpoints

Perform Calculations:
- Addition: ```GET /api/calculate/sum?a={number}&b={number}```
- Subtraction: ```GET /api/calculate/subtract?a={number}&b={number}```
- Multiplication: ```GET /api/calculate/multiply?a={number}&b={number}```
- Division: ```GET /api/calculate/divide?a={number}&b={number}```

Get Results:
- ```GET /api/calculate/results/{operationId}```

### Example Usage

Request an addition:
```bash
curl "http://localhost:8080/api/calculate/sum?a=1.5&b=2.3"
```

Example response headers:
```bash
< HTTP/1.1 202 Accepted
< X-Operation-ID: 4087ae4c-8b51-4bd4-b000-20474bb17c4d
```

