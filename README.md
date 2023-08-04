# URL shortener

## Table of content

- [Description](#description)
- [Stack](#stack)
- [Prerequisites](#prerequisites)
- [Architecture Diagram](#architecture-diagram)
- [Environment](#environment)
- [Installation and running](#installation-and-running)
- [Endpoints](#endpoints)

## Description

URL Shortener is a microservice developed in Java 8+ using Spring Boot and MongoDB database. It offers functionality to shorten long URLs by providing short URLs that redirect users to the original URL. Also, you can get access statistics to the shortened URLs.

## Stack

- **[Java JDK 17+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)**: Programming Language
- **[Spring Boot](https://spring.io/projects/spring-boot)**: Web Framework
- **[Spring Data/JPA](https://spring.io/projects/spring-data-jpa)**: Persistence Framework for MongoDB
- **[MongoDB](https://www.mongodb.com/)**: Database
- **[Docker](https://www.docker.com/)**: Containerization Platform
- **[Gradle](https://gradle.org/)**: Build Tool

## Prerequisites

- **[Required]** [Java JDK 17+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **[Required]** [Docker 19+](https://docs.docker.com/install/)
- **[Required]** [Docker Compose 1.23+](https://docs.docker.com/compose/install/#install-compose)
- **[Required]** [Gradle 6+](https://gradle.org/install/)
- **[Optional]** [Jetbrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=linux)
- **[Optional]** [Postman](https://www.postman.com/downloads/)
- **[Optional]** [MongoDB Compass](https://www.mongodb.com/products/compass)

## Architecture Diagram

Work in Progress

## Environment

- **Localhost:** http://localhost:8080

## Installation and running

- Clone the repository
- Open the project in your IDE
- Configure the environment variables in the application.properties file
- Configure the environment variables in the docker-compose.yml file
- MongoDB is running in a docker container. You can use the provided docker-compose file to run it.
- Run the application
- Open Postman and test the endpoints
- Open MongoDB Compass and check the database

```bash


```bash

**_Note:_** _Integration tests require a running MongoDB instance. You can use the provided docker-compose file to run it._

```bash

# Run MongoDB

docker-compose up -d

```

## **Endpoints**

### Shorten URL

**URL:** POST `/url-shortner`
**Content-Type:** `application/json`

Send a POST request with the following JSON body:

```json
{
  "url": "LONG_URL_HERE"
}
```

response:

```
shortUrl

```

### Get access statistics

**URL:** GET `/stats`

response:

```json
{
  "url": "URL",
  "accessCount": 1
}
```

### Feel free to reach out if you have any questions or need further assistance.

