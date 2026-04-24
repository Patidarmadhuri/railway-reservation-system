## Online Railway Reservation System

This repository contains a simple **online railway reservation system** built with a Java Spring Boot backend and a lightweight HTML/CSS/JavaScript frontend.  The application lets users search for trains, book tickets, and view their booking history.

### Features

* **Train search** – query available trains by origin, destination and date.
* **Ticket booking** – book seats on a selected train by entering passenger details and seat count.
* **Booking history** – retrieve a list of all bookings made by a user (based on a mock user id).
* **REST API** – the backend exposes endpoints for trains and reservations under `/api`.
* **Clean separation** – the project is split into `backend/` for Spring Boot code and `frontend/` for static HTML/CSS/JavaScript.

### Tech Stack

* **Backend:** Java, Spring Boot (Web, JPA), Hibernate, H2 (for development)
* **Frontend:** Plain HTML/CSS/JavaScript (no framework)
* **Database:** H2 (configurable to MySQL/PostgreSQL)
* **Build tool:** Maven

### Running Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/Patidarmadhuri/railway-reservation-system.git
   cd railway-reservation-system
   ```

2. Start the backend:

   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

   The REST API will be available at `http://localhost:8080/api/trains`.

3. Open the frontend:

   Simply open `frontend/index.html` in your browser.  You may need to adjust the API base URL in `frontend/script.js` to match your backend host/port.

### Repository Structure

```
railway-reservation-system/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/example/railway/
│       ├── RailwayReservationApplication.java
│       ├── entity/
│       │   ├── Train.java
│       │   └── Reservation.java
│       ├── repository/
│       │   ├── TrainRepository.java
│       │   └── ReservationRepository.java
│       ├── service/
│       │   ├── TrainService.java
│       │   └── ReservationService.java
│       └── controller/
│           ├── TrainController.java
│           └── ReservationController.java
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
└── README.md
```

### Screenshots

Add screenshots of the train search page and booking form once implemented.

### Topics/Tags

`java`, `spring-boot`, `html`, `css`, `sql`, `full-stack`, `railway`