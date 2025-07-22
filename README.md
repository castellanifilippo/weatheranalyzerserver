# WeatherAnalyzerServer

WeatherAnalyzerServer is a university project developed in Java (Spring Boot) that provides RESTful APIs for meteorological data analysis, management, and user-specific bookmarks for cities or coordinates. The system integrates MySQL/MariaDB as the database and uses external services (Open-Meteo, Nominatim) for weather and geolocation data. User authentication and session management are implemented securely, making this a robust example for educational purposes.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Main Features](#main-features)
- [Architecture and Data Flow](#architecture-and-data-flow)
- [Setup and Configuration](#setup-and-configuration)
- [Database Structure](#database-structure)
- [API Details](#api-details)
- [Authentication and Security](#authentication-and-security)
- [Usage Examples](#usage-examples)
- [Dependencies](#dependencies)
- [Technical Notes](#technical-notes)
- [Author](#author)

---

## Project Overview

This backend server allows users to register, authenticate, and interact with weather data. Users can retrieve current and historical weather for specific cities or coordinates, update the database with the latest weather information, and maintain a personalized list of up to 10 favorite locations (bookmarks). The project demonstrates the integration of external APIs, secure user management, and modern backend design patterns suitable for academic settings.

---

## Main Features

- **User Management:**
  - Secure registration and login with hashed passwords (BCrypt).
  - Stateless authentication using session tokens (1-hour validity).
- **Weather Data Handling:**
  - Retrieve current and last 10 historical weather records by city or coordinates.
  - Update and store the latest weather data using the Open-Meteo API.
  - Automatic addition of new cities using Nominatim (OpenStreetMap) when not found in the database.
- **Personal Bookmarks:**
  - Each user can add, remove, and view up to 10 favorite cities or coordinates.
  - All bookmark operations are protected by user authentication.
- **Database Initialization:**
  - On startup, the server checks for the required schema and creates tables if necessary (via `init.sql`).
- **Logging:**
  - All significant operations are logged for transparency and debugging.

---

## Architecture and Data Flow

- **Backend:** Java 17+, Spring Boot, RESTful APIs, Spring Data repositories.
- **Database:** MySQL/MariaDB (default schema: `weather_schema`), JDBC connection.
- **External APIs:**
  - [Open-Meteo](https://open-meteo.com/) for real-time weather data.
  - [Nominatim](https://nominatim.openstreetmap.org/) for city name and coordinate resolution.
- **Typical Flow:**
  1. User registers and receives a session token.
  2. User can query or update weather data, and manage bookmarks using this token.
  3. All sensitive operations are logged.

---

## Setup and Configuration

1. **Clone the repository**
   ```sh
   git clone https://github.com/castellanifilippo/weatheranalyzerserver.git
   cd weatheranalyzerserver
   ```

2. **Database setup**
   - Start MySQL/MariaDB on `localhost:3306`.
   - Default user/password: `root`/`root` (you can change this in `src/main/resources/application.properties`).
   - The default schema is `weather_schema`. It will be created and initialized automatically if missing.

3. **Build and run**
   ```sh
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   The server will start at `localhost:8080`.

---

## Database Structure

- **Users:** username (max 10 chars), password (hashed)
- **Sessions:** token, username, expiry date
- **Cities:** name, latitude, longitude
- **Weather:** city, latitude, longitude, temperature, humidity, precipitation, snow, timestamp
- **Bookmarks:** username, city/coordinates

---

## API Details

### User Endpoints
- `POST /sign-in`  
  Register a new user.  
  Body: `{ "username": "test", "password": "testpass" }`

- `POST /login`  
  User login.  
  Body: `{ "username": "test", "password": "testpass" }`  
  Response: `{ "message": "Login successful", "token": "<token>" }`

### Weather Endpoints
- `GET /weather/city/{name}`  
  Get current weather by city name.

- `GET /weather/city10/{name}`  
  Get last 10 weather records by city name.

- `GET /weather/coord={lat}&{lon}`  
  Get current weather by coordinates.

- `GET /weather/coord10={lat}&{lon}`  
  Get last 10 weather records by coordinates.

- `GET /weather/update/city/{name}`  
  Update and return the latest weather for a city.

- `GET /weather/update/coord={lat}&{lon}`  
  Update and return the latest weather for coordinates.

### Bookmark Endpoints (require token)
- `POST /bookmark/add`  
  Add a bookmark.  
  Body: `{ "username": "<token>", "city": "Pisa" }`

- `POST /bookmark/remove`  
  Remove a bookmark.  
  Body: `{ "username": "<token>", "city": "Pisa" }`

- `POST /bookmark/get`  
  Get all bookmarks for the user.  
  Body: `"token"`

---

## Authentication and Security

- All write operations (including bookmark management) require authentication via session token.
- Passwords are always hashed using BCrypt before being stored.
- Session tokens are UUIDs (without hyphens), expiring after 1 hour.
- Usernames must be unique and follow basic format constraints (max 10 alphanumeric characters, no spaces).

---

## Usage Examples

**User registration**
```sh
curl -X POST http://localhost:8080/sign-in -H "Content-Type: application/json" -d '{"username":"student1","password":"mypassword"}'
```

**Login**
```sh
curl -X POST http://localhost:8080/login -H "Content-Type: application/json" -d '{"username":"student1","password":"mypassword"}'
```

**Request weather for a city**
```sh
curl http://localhost:8080/weather/city/Pisa
```

**Add a bookmark**
```sh
curl -X POST http://localhost:8080/bookmark/add -H "Content-Type: application/json" -d '{"username":"<token>","city":"Pisa"}'
```

---

## Dependencies

- Java 17+
- Spring Boot (Web, Data JPA)
- MySQL/MariaDB Driver
- Gson (for JSON parsing)
- BCrypt (for password hashing)
- Log4j (for logging)

---

## Technical Notes

- Database connection settings can be changed in `src/main/resources/application.properties`.
- The project is designed for educational purposes and can be easily extended or containerized (e.g., via Docker).
- API usage can be tested with curl, Postman, or similar HTTP clients.
- Code and comments are in Italian, but the structure is standard and easy to follow.
- On startup, the backend will attempt to auto-create the database schema and tables if missing.

---

## Author

Developed by [castellanifilippo](https://github.com/castellanifilippo) as a university project.
