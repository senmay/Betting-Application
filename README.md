# Sports Betting Application

This is a sports betting application built using Spring Boot and Hibernate. The application provides a RESTful API for users to place bets on sports events and manages the user balance and bet results. Application is still in development. In [wikis](https://github.com/senmay/Betting-Application/wiki) there are informations about validations and design of this app and also there is scenario for creating bet using request.

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

## Controllers

- GoogleSheetsController: This controller manages interactions with Google Sheets, including creating, deleting, and exporting sheets.
- UserJsonController: Manages user-related endpoints, including registration, retrieval, and updating user details.
- TeamController: Manges teams inclugin import teams from Google Sheets and handles runtime exceptions.
- MatchResultController: Controller is responsible for saving and retrieving match results.
- MatchController: Manages match-related endpoints, including creating, retrieving, updating, and deleting matches.
- DbErrorController: This controller handles error-related endpoints, such as retrieving all errors.
- BetController: Manages bet-related endpoints, including creating and retrieving bets for a specific user.

## Services

- UserService: Handles user registration, authentication, and user-related operations
- BetService: Manages bet-related operations, including placing bets, retrieving bets, and filtering bets
- MatchService: Handles match-related operations, including creating, retrieving, and updating match details
- OddsService: Interacts with the Odds API to fetch and manage real-time odds data
- BalanceService: Updates user balances upon match result updates and handles winning and losing bets
- DbTeamPersistenceService: Manages team-related operations, such as saving, retrieving, updating, and deleting teams
- GoogleSheetsService: Interacts with Google Sheets to add, delete, and export sheets, as well as read and write team data
- GeneralValidator: Validates objects and handles exceptions for various input types


## Acknowledgments

- Spring Boot and Hibernate for providing the framework and ORM
- H2 Database for development and PostgreSQL for production
- Maven for project management and build automation
- All other open-source libraries and tools used in this project
