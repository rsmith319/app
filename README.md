A backend REST API for an appointment booking system built to manage users, service providers, services, and appointment scheduling. This project is designed to support a frontend web or mobile application where customers can register, browse providers, book appointments, and manage their bookings.

## Features

- User registration and authentication
- Provider registration and profile management
- Service creation and management by providers
- Appointment booking by customers
- Appointment status tracking
- CRUD operations for core resources
- RESTful API design
- Database persistence with SQL
- Ready to connect to a frontend application

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL**
- **Hibernate**
- **Maven**

## Project Purpose

This API was built for an appointment booking system where:

- **Customers** can create accounts, browse available services, and book appointments
- **Providers** can manage their service information and availability
- **Appointments** connect customers with providers for specific services and time slots

The goal is to provide a clean backend architecture that can be expanded with authentication, authorization, notifications, payment integration, and frontend support.

## Main Entities

### User
Represents a customer using the booking platform.

Typical fields may include:
- id
- firstName
- lastName
- email
- password
- phoneNumber
- role

### Provider
Represents a service provider who offers appointments.

Typical fields may include:
- id
- businessName
- providerName
- email
- phoneNumber
- specialization
- availability

### Service
Represents a bookable service offered by a provider.

Typical fields may include:
- id
- name
- description
- duration
- price
- providerId

### Appointment
Represents a booking made by a customer for a specific provider/service.

Typical fields may include:
- id
- appointmentDate
- appointmentTime
- status
- notes
- customer
- provider
- service

## API Endpoints

> These are example endpoint groups. Update them to match your actual routes.

### User Routes
- `POST /api/users/register` - Register a new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Provider Routes
- `POST /api/providers` - Create provider
- `GET /api/providers` - Get all providers
- `GET /api/providers/{id}` - Get provider by ID
- `PUT /api/providers/{id}` - Update provider
- `DELETE /api/providers/{id}` - Delete provider

### Service Routes
- `POST /api/services` - Create a service
- `GET /api/services` - Get all services
- `GET /api/services/{id}` - Get service by ID
- `PUT /api/services/{id}` - Update service
- `DELETE /api/services/{id}` - Delete service

### Appointment Routes
- `POST /api/appointments` - Book an appointment
- `GET /api/appointments` - Get all appointments
- `GET /api/appointments/{id}` - Get appointment by ID
- `PUT /api/appointments/{id}` - Update appointment
- `DELETE /api/appointments/{id}` - Cancel appointment

## How It Works

1. A user registers as a customer
2. A provider creates a profile and adds services
3. The customer browses providers and services
4. The customer books an appointment
5. The provider can view and manage booked appointments
6. The appointment status can be updated as needed

## Database

This project uses a relational SQL database with entity relationships such as:

- One user can have many appointments
- One provider can have many services
- One provider can have many appointments
- One service can belong to one provider
- One appointment belongs to one user and one provider

## Getting Started

### Prerequisites

Make sure you have installed:

- Java 17+  
- Maven  
- PostgreSQL  
- IDE such as IntelliJ IDEA, Eclipse, or VS Code  

### Clone the Repository

```bash
git clone https://github.com/rsmith319/app.git
cd app
