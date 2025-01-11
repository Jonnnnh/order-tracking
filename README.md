# Order Tracking System

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Docker](https://img.shields.io/badge/Docker-20.10.7-blue.svg)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-3.x-lightgrey.svg)

## Description

**Order Tracking System** is a microservice application for managing orders and processing notifications about their creation. The architecture includes two main services:

1. **Order Service** – is responsible for creating and managing orders.
2. **Notification Service** – processes order creation events and emulates notifications.

Interaction between services is carried out via **Apache Kafka**, providing reliable and scalable message transmission. The system is containerized using **Docker** for easy deployment.

---

## Functionality

### Order Service

- **REST API** for creating and receiving orders.
- Saving an order to a database (**PostgreSQL**).
- Sending an `OrderCreated` event to Kafka when an order is created.

### Notification Service

- Subscription to the Kafka topic `orders`.
- Processing `OrderCreated` events and emulating notifications (logging or saving to a database).

---

## Technologies

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Messaging:** Apache Kafka
- **Database:** PostgreSQL
- **Containerization:** Docker, Docker Compose
- **Tools:** Spring Data JPA, Spring for Apache Kafka, Flyway, Lombok, MapStruct

## Starting the Project

### Prerequisites

- **Docker** and **Docker Compose** installed on your machine.
- **Git** for cloning the repository.

---

### Steps

1. **Clone the Repository**

```bash
git clone https://github.com/Jonnnnh/order-tracking.git
cd order-tracking
```

2. **Run with Docker Compose**

Make sure ports `5432`, `2181`, `9092`, `8080` and `8081` are free.

```bash
docker-compose up --build
```

This will start all services: PostgreSQL, Zookeeper, Kafka, Order Service and Notification Service.

3. **Create a Kafka Topic**

In a new terminal, run:

```bash
docker exec -it kafka kafka-topics --create \
--topic orders \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1
```

## Using the API

### Creating an Order

- **Endpoint:** `POST http://localhost:8080/orders`

- **Request Body:**

```json
{
"product": "Laptop",
"quantity": 1,
"price": 1500.00
}
```

- **Request Example:**

```bash
curl -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-d '{
"product": "Laptop",
"quantity": 1,
"price": 1500.00
}'
```

- **Response:**

```json
{
"id": 1,
"product": "Laptop",
"quantity": 1,
"price": 1500.00
}
```

### Getting Orders

- **Endpoint:** `GET http://localhost:8080/orders?page=0&size=10`

- **Request Example:**

```bash
curl http://localhost:8080/orders?page=0&size=10
```

- **Response:**

```json
[
{
"id": 1,
"product": "Laptop",
"quantity": 1,
"price": 1500.00
}
]
```

## Checking the Job

1. **Create an order** via the Order Service.

2. **Check the Notification Service logs** to make sure the event was processed:

```
Received event: OrderCreated { "id": 1, "product": "Laptop", "quantity": 1, "price": 1500.00 }
```

## Shutting Down the Job

To stop all containers, press `Ctrl+C` in the terminal with Docker Compose running, or run:

```bash
docker-compose down