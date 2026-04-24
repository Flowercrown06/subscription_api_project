# 🚀 Subscription Management System (Nexus API)

[![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.3.4-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)](https://www.postgresql.org/)

This is a robust **Subscription Management API** built with Spring Boot 3.3.4. It handles user tiers, secure authentication, and automated notifications, all while following professional architectural patterns.

---

## ✨ Key Features
* 🔐 **Secure Auth:** JWT-based authentication and Role-Based Access Control (RBAC).
* 💳 **Subscription Logic:** Dynamic plan upgrades (`FREE`, `PREMIUM`, `ENTERPRISE`) with real-time role synchronization.
* 📧 **Email Notifications:** Automated mail alerts for subscription status changes.
* 📱 **QR Integration:** Built-in QR code generation for digital subscription verification.
* 🗺️ **High Performance:** Optimized Entity-to-DTO mapping using **MapStruct**.
* 📖 **API Docs:** Fully documented with **Swagger UI / OpenAPI 3**.
* 🐳 **Containerized:** Seamless deployment using **Docker** and **Docker Compose**.

---

## 🛠️ Technical Stack
* **Backend:** Java 17, Spring Boot 3.3.4
* **Security:** Spring Security, JWT (JJWT)
* **Persistence:** Spring Data JPA, PostgreSQL, H2 (for testing)
* **Mapping:** MapStruct 1.5.5
* **Utilities:** Lombok, ZXing (QR Codes), Spring Mail
* **Documentation:** SpringDoc OpenAPI

---

## 🚦 API Endpoints (Quick Overview)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Public | Create a new user account |
| `POST` | `/api/auth/login` | Public | Authenticate & receive JWT |
| `POST` | `/api/subscriptions/upgrade` | User | Change current plan & update Role |
| `GET` | `/api/v1/qr/generate` | User | Generate subscription QR code |
| `GET` | `/swagger-ui.html` | Public | Interactive API Documentation |

---
## 📁 Project Architecture
The project follows a clean N-tier architecture:

* **Controller:** REST endpoints and request validation.

* **Service:** Business logic for subscriptions.

* **DTO/Mapper:** Decoupled data transfer using MapStruct.

* **Security:** JWT filters, Authentication providers, and Security context.

* **Repository:** Efficient data access layer for PostgreSQL.

---
## 🚀 How to Run

### 1. Manual Setup
Ensure PostgreSQL is running.

Update src/main/resources/application.yml with your database credentials.

**Run:**  ./gradlew bootRun


### 2. Using Docker (Fastest)
```bash
# Clone the repository
git clone [https://github.com/Flowercrown06/subscription_api_project.git](https://github.com/Flowercrown06/subscription_api_project.git)

# Navigate to project folder
cd subscription_api_project

# Build and start all services
docker-compose up --build