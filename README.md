# urlshortnerapi

A high-performance RESTful backend service built with Spring Boot 3 and Java 21 to compress long URLs into short, trackable links.

## 🚀 Features
* **URL Compression**: Generates unique, lightweight short codes for long URLs.
* **Redis Caching**: Caches original URLs to ensure ultra-fast redirection speeds.
* **MySQL Persistence**: Stores durable URL mapping records and analytics safely.
* **Click Analytics**: Tracks total visits and access metrics per shortened link.

## 🛠️ Tech Stack
* **Java 21** (Virtual Threads optimized)
* **Spring Boot 3.x** (Spring Web, Spring Data JPA, Spring Data Redis)
* **MySQL** (Relational database for persistent storage)
* **Redis** (In-memory data grid for high-speed cache)
* **Apache Maven** (Dependency management)

## 📦 Prerequisites
* Java Development Kit (JDK) 21 installed
* Apache Maven 3.9+ installed
* Running instance of MySQL Server
* Running instance of Redis Server

## ⚙️ Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com
cd urlshortnerapi
```

### 2. Configure Environment
Update your credentials in `src/main/resources/application.properties`:
```properties
server.port=8080

# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Redis Configuration
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=your_redis_password
```

### 3. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

## 🔌 API Endpoints

### Shorten a URL
* **Endpoint:** `POST /api/v1/shorten`
* **Request Body:**
```json
{
  "longUrl": "https://example.com"
}
```
* **Response:**
```json
{
  "shortCode": "aB7x9K",
  "shortUrl": "http://localhost:8080/aB7x9K"
}
```

### Redirect Link (Uses Redis Cache)
* **Endpoint:** `GET /{shortCode}`
* **Action:** Fetches from Redis cache (or falls back to MySQL) and redirects with HTTP 302.

### Get Analytics
* **Endpoint:** `GET /api/v1/analytics/{shortCode}`
* **Response:**
```json
{
  "shortCode": "aB7x9K",
  "clickCount": 142,
  "createdDate": "2026-08-21T13:12:00Z"
}
```
