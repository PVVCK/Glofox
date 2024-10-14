# Glofox Assignment - Gym Class and Booking Management API

## Overview

This Spring Boot application is designed to manage classes and bookings for gyms, studios, and fitness boutiques. The API provides functionalities to create gym classes, book classes, and view all available classes and bookings.

## Features

- **Create Class**: Allows the creation of gym classes with schedules and capacity.
- **Book Class**: Enables users to book available gym classes.
- **View Classes**: Fetches all available gym classes.
- **View Bookings**: Fetches all current bookings.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Validation**
- **Jakarta Validation**
- **SLF4J (Logging)**
- **JUnit 5** (for unit testing)
- **Mockito** (for mocking objects in tests)
- **Maven** (for dependency management)


## Note on Data Persistence 

The application uses List's(acts as in-memory database) for simplicity. In a real-world scenario, you would replace this
with a proper database like MySQL or PostgreSQL.


## Project Structure

```bash
src
├── main
│   ├── java
│   │   └── com
│   │       └── Glofox
│   │           └── assignment
│   │               ├── controller   # Contains REST controllers (StudioController)
│   │               ├── dto          # Request classes (ClassRequest, BookingRequest)
│   │               ├── model        # Models for classes and bookings (ClassModel, BookingModel)
│   │               ├── response     # Standard API Response (APIResponse)
│   │               ├── util         # Utility classes (ExecutionTimeLogger)
│   └── resources
│       └── application.properties   # Application configuration
└── test
    └── java
        └── com
            └── Glofox
                └── assignment
                    └── controller   # Unit tests for StudioController



API Endpoints
1. Create a Class
POST http://localhost:9001/glofox/class

Request Body:

{
  "name": "Pilates",
  "startDate": "2024-12-01",
  "endDate": "2024-12-20",
  "capacity": 10
}

Response:

{
    "status": "Success",
    "requestDetails": 
    {
        "name": "Pilates",
        "schedule": [
            "2024-12-01",
            "2024-12-02",
            "2024-12-03",
            "2024-12-04",
            "2024-12-05",
            "2024-12-06",
            "2024-12-07",
            "2024-12-08",
            "2024-12-09",
            "2024-12-10",
            "2024-12-11",
            "2024-12-12",
            "2024-12-13",
            "2024-12-14",
            "2024-12-15",
            "2024-12-16",
            "2024-12-17",
            "2024-12-18",
            "2024-12-19",
            "2024-12-20"
        ],
        "capacity": 10
    }
}


2. Book a Class
POST http://localhost:9001/glofox/bookClass

Request Body:

{
  "name": "Chaitanya",
  "date": "2024-12-01",
  "className": "Pilates"
}

Response:

{
    "status": "Success",
    "requestDetails":
     {
        "name": "Chaitanya",
        "date": "2024-12-01",
        "className": "Pilates"
    }
}

3. Get All Classes
GET http://localhost:9001/glofox/classes

Response:

[
    {
        "name": "Pilates",
        "schedule": [
            "2024-12-01",
            "2024-12-02",
            "2024-12-03",
            "2024-12-04",
            "2024-12-05",
            "2024-12-06",
            "2024-12-07",
            "2024-12-08",
            "2024-12-09",
            "2024-12-10",
            "2024-12-11",
            "2024-12-12",
            "2024-12-13",
            "2024-12-14",
            "2024-12-15",
            "2024-12-16",
            "2024-12-17",
            "2024-12-18",
            "2024-12-19",
            "2024-12-20"
        ],
        "capacity": 10
    },
    {
        "name": "Circuit Training",
        "schedule": [
            "2025-01-01",
            "2025-01-02",
            "2025-01-03",
            "2025-01-04",
            "2025-01-05",
            "2025-01-06",
            "2025-01-07",
            "2025-01-08",
            "2025-01-09",
            "2025-01-10",
            "2025-01-11",
            "2025-01-12",
            "2025-01-13",
            "2025-01-14",
            "2025-01-15",
            "2025-01-16",
            "2025-01-17",
            "2025-01-18",
            "2025-01-19",
            "2025-01-20",
            "2025-01-21",
            "2025-01-22",
            "2025-01-23",
            "2025-01-24",
            "2025-01-25",
            "2025-01-26",
            "2025-01-27",
            "2025-01-28",
            "2025-01-29",
            "2025-01-30",
            "2025-01-31",
            "2025-02-01",
            "2025-02-02",
            "2025-02-03",
            "2025-02-04",
            "2025-02-05",
            "2025-02-06",
            "2025-02-07",
            "2025-02-08",
            "2025-02-09",
            "2025-02-10",
            "2025-02-11",
            "2025-02-12",
            "2025-02-13",
            "2025-02-14",
            "2025-02-15",
            "2025-02-16",
            "2025-02-17",
            "2025-02-18",
            "2025-02-19",
            "2025-02-20"
        ],
        "capacity": 10
    }
]


4. Get All Bookings
GET http://localhost:9001/glofox/bookings

Response:

[
    {
        "name": "Chaitanya",
        "classDate": "2024-12-01",
        "className": "Pilates"
    },
    {
        "name": "Karthik",
        "classDate": "2025-01-01",
        "className": "Circuit Training"
    }
]

