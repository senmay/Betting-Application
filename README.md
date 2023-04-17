# Sports Betting Application

This is a sports betting application built using Spring Boot and Hibernate. The application provides a RESTful API for users to place bets on sports events and manages the user balance and bet results. Application is still in development. In wikis there is scenario for creating bet using request.

## Overview

The application is designed with a focus on modularity, scalability, and maintainability. It follows best practices for RESTful API design and uses Spring Boot and Hibernate to create a robust, extensible backend system.

## Features

- User registration and authentication through header.
- Role-based access control for different user roles (e.g., regular users, admins)
- Bets management (placing bets, retrieving bets, filtering bets by user or match)
- Matches management (creating matches, retrieving match information, updating match details)
- Automatic balance update after a match result is posted, with proper handling of winning and losing bets
- Data validation for incoming requests, with custom exception handling and error responses
- Pagination and sorting support for listing endpoints
- Unit and integration testing for key components of the application

## Technologies

- Java 11
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (for development)
- Maven
- RESTful API
- Lombok and MapStruct for reducing boilerplate code
- Aspect-Oriented Programming (AOP) for updating balances after entering match result

## Acknowledgments

- Spring Boot and Hibernate for providing the framework and ORM
- H2 Database for development and PostgreSQL for production
- Maven for project management and build automation
- All other open-source libraries and tools used in this project
