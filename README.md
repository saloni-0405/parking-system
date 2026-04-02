"# parking-system" 
# 🅿️ ParkEase – Smart Parking Slot Finder System

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?style=flat-square&logo=bootstrap)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

A full-stack **web-based Parking Slot Management System** built with Spring Boot and MySQL. Users can view real-time parking availability, book slots, and track parking duration. Admins can manage slots from a dedicated control panel.

---

## 🖼️ Screenshots

### 🟢 User – Slot Booking Page
> View live slot availability, book a slot with name & vehicle number, release when done.

### 🔴 Admin – Control Panel
> Add slots (bulk or manual), toggle status, view all bookings and history.

---

## ✨ Features

- 🟢 **Real-time slot availability** – live green/red status cards
- 🚗 **Book a slot** – enter name & vehicle number, slot turns occupied instantly
- ⏱️ **Timer tracking** – records start time, end time, and calculates duration
- 🔓 **Release slot** – frees up the slot and completes the booking
- 🛡️ **Admin panel** – add/delete slots, bulk add by zone, toggle status manually
- 📋 **Booking history** – searchable log of all past and active bookings
- 🔄 **Auto-refresh** – UI updates every 30 seconds automatically

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Spring Boot 3.2 |
| Database | MySQL 8.0 |
| ORM | Spring Data JPA / Hibernate |
| Frontend | HTML5, CSS3, Bootstrap 5.3 |
| API Style | REST API |
| Build Tool | Maven |

---

## 📁 Project Structure

```
parking-system/
│
├── src/main/java/com/parking/
│   ├── ParkingSystemApplication.java     ← Main entry point
│   │
│   ├── controller/
│   │   ├── SlotController.java           ← Slot REST APIs
│   │   └── BookingController.java        ← Booking REST APIs
│   │
│   ├── service/
│   │   ├── SlotService.java              ← Slot business logic
│   │   └── BookingService.java           ← Booking business logic
│   │
│   ├── repository/
│   │   ├── SlotRepository.java           ← Slot DB queries
│   │   └── BookingRepository.java        ← Booking DB queries
│   │
│   └── model/
│       ├── ParkingSlot.java              ← Slot entity
│       └── Booking.java                  ← Booking entity
│
└── src/main/resources/
    ├── application.properties            ← DB config
    └── static/
        ├── index.html                    ← User page
        ├── admin.html                    ← Admin panel
        └── history.html                  ← Booking history
```

---

## 🗄️ Database Schema

### `parking_slot`
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-generated ID |
| slot_number | VARCHAR | Slot name e.g. A1, B3 |
| status | ENUM | AVAILABLE / OCCUPIED |
| area | VARCHAR | Zone A / B / C |

### `booking`
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-generated ID |
| slot_id | BIGINT (FK) | References parking_slot |
| user_name | VARCHAR | Name of user |
| vehicle_number | VARCHAR | Vehicle plate number |
| start_time | DATETIME | Booking start time |
| end_time | DATETIME | Booking end time |
| status | ENUM | ACTIVE / COMPLETED |

---

## 🔗 REST API Endpoints

### Slot APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/slots` | Get all slots |
| GET | `/slots/available` | Get available slots only |
| POST | `/slots` | Add new slot (Admin) |
| PUT | `/slots/{id}/status` | Update slot status |
| DELETE | `/slots/{id}` | Delete slot (Admin) |

### Booking APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/book` | Book a slot |
| PUT | `/release/{bookingId}` | Release a slot |
| GET | `/bookings` | Get all bookings |
| GET | `/bookings/active` | Get active bookings only |

---

## ⚙️ Prerequisites

Make sure you have these installed:

- ✅ Java JDK 17+
- ✅ Maven 3.6+
- ✅ MySQL 8.0+
- ✅ VS Code (with Java Extension Pack)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/parking-system.git
cd parking-system
```

### 2. Setup MySQL Database

Open MySQL and run:

```sql
CREATE DATABASE IF NOT EXISTS parking_db;
```

### 3. Configure Database Credentials

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the Project

```bash
mvn spring-boot:run
```

Or in VS Code → open `ParkingSystemApplication.java` → click ▶ Run

### 5. Open in Browser

| Page | URL |
|------|-----|
| 🟢 User Page | http://localhost:8080/index.html |
| 🛡️ Admin Panel | http://localhost:8080/admin.html |
| 📋 History | http://localhost:8080/history.html |

---

## 📖 How to Use

### As a User
1. Go to `http://localhost:8080/index.html`
2. View green (available) and red (occupied) slots
3. Click **Book Now** on any green slot
4. Enter your name and vehicle number → **Confirm**
5. When leaving, click **Release** in the Active Bookings table

### As Admin
1. Go to `http://localhost:8080/admin.html`
2. Click **Zone A / B / C** buttons to bulk-add 5 slots per zone
3. Toggle slot status manually if needed
4. View all bookings and occupancy stats

---

## 🐛 Troubleshooting

| Error | Fix |
|-------|-----|
| `Access denied for user root` | Check password in `application.properties` |
| `Communications link failure` | Start MySQL service |
| `Port 8080 already in use` | Change `server.port=8081` in properties |
| White screen in browser | Make sure Spring Boot is running |

---

## 👨‍💻 Author

**Your Name**  
📧 your.email@example.com  
🔗 [GitHub](https://github.com/YOUR_USERNAME)

---

## 📄 License

This project is licensed under the MIT License.

---

⭐ **If you found this helpful, please give it a star!**
