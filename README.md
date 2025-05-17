# User Producer Microservice

This microservice is responsible for accepting user data via a REST API and publishing the data to a RabbitMQ queue. Another service (user-consumer-microservice) listens to this queue and processes the user data.

---

## 🧩 Tech Stack

- Java 17+
- Spring Boot
- Spring AMQP (RabbitMQ)
- RabbitMQ
- Maven

---

## 📦 Features

- POST endpoint to send user data
- Publishes user data to RabbitMQ queue
- Sends messages in JSON format

---

