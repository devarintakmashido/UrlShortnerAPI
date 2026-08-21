# 🔗 URL Shortener API (Bitly Clone)

A high-performance REST API that takes long URLs and generates short, shareable links. Built with a focus on speed and scalability, this application utilizes in-memory caching to handle high-traffic redirects and automated background tasks to maintain database health.

---

## 🚀 Features

* **Instant Redirects:** Utilizes Redis caching to intercept and serve high-volume URL requests in milliseconds, completely bypassing the database.
* **Link Generation:** Generates unique, 6-character alphanumeric short codes for any valid URL.
* **Automated Janitor Tasks:** Implements Spring Scheduling to run a nightly cron job that automatically purges expired URLs from the database.
* **Fully Containerized:** Packaged with Docker for seamless, platform-agnostic deployment.
* **CI/CD Pipeline:** Integrated with GitHub Actions to automatically build and verify code integrity on every push.

---

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3
* **Database:** MySQL (Persistent Storage)
* **Cache:** Redis (In-Memory Fast Retrieval)
* **DevOps:** Docker, GitHub Actions

---

## ⚙️ Getting Started (Local Development)

### Prerequisites
* [Docker Desktop](https://www.docker.com/products/docker-desktop) installed and running.
* Java 21 installed locally.

### Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR-USERNAME/url-shortener-api.git](https://github.com/YOUR-USERNAME/url-shortener-api.git)
   cd url-shortener-api

   
