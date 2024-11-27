# 🏀 **NBA Player Directory**  
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/karimaljundi/NBAMatchPredictor/actions)  
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)  
[![Docker](https://img.shields.io/badge/docker-ready-blue)](https://hub.docker.com)  
[![AWS Deployment](https://img.shields.io/badge/AWS-Deployed-orange)](http://your-s3-bucket-link)

A **full-stack application** that provides detailed player information, leveraging technologies like **Spring Boot**, **React**, **PostgreSQL**, and **AWS**. Supports **CRUD operations**, dynamic filtering, and a **responsive frontend** for seamless user interactions.  

---

## ✨ **Features**  

✔️ Scrape and store player data using **Selenium** and **BeautifulSoup**.  
✔️ RESTful APIs for filtering by **name, team, and position**.  
✔️ **Dockerized deployment** for cross-platform compatibility.  
✔️ Hosted on **AWS EC2**, with the frontend served via an **S3 Bucket**.  

---

## 🛠️ **Tech Stack**  

| **Category**      | **Technologies**                           |
|--------------------|-------------------------------------------|
| **Backend**        | Spring Boot, PostgreSQL, Docker           |
| **Frontend**       | React, AWS S3                            |
| **Scraping**       | Selenium, BeautifulSoup, Pandas           |
| **Hosting**        | AWS EC2, RDS                             |

---

## 🚀 **Installation**  

### 1️⃣ Clone the Repository  
```bash
git clone https://github.com/karimaljundi/NBAMatchPredictor.git
```
### 2️⃣ Navigate to the backend folder and run:

```bash
./mvnw spring-boot:run
```
### 3️⃣ Open another terminal, navigate to the frontend folder, and run:
```bash
npm install
npm start
```

## 📡 API Reference

#### Get all players

```http
GET /api/v1/player
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `team` | `string` | **Optional**.Filter players by player's team |
| `name` | `string` | **Optional**.Filter players by player name |
| `pos` | `string` | **Optional**.Filter players by player's position |

- Returns a list of all players.
- If query parameters team, name, or pos are provided, filters the results accordingly.
- If both team and pos are given, filters players by team and position.

#### Add a new player

```http
POST /api/v1/player
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `player`      | `json` | **Required**. Player Object to add |

Example:
```json
{
  "name": "John Doe",
  "team": "Lakers",
  "position": "Forward",
  "age": 25
}
```

- 201 Created: Returns the created player object.


```http
PUT /api/v1/player
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `player`      | `json` | **Required**. Updated player data |

- Updates an existing player's details in the database.
- 200 OK: Returns the updated player object.
- 404 Not Found: If the player doesn't exist.

```http
DELETE /api/v1/player/{playerName}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `playername`      | `string` | **Required**. Name of the player |

- Deletes the player with the given name from the database.
- 200 OK: Returns a success message.
