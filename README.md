# saga-pattern
Orchestration-based saga

## Technologies
Here are the technologies used in this project:
- Java 17
- Spring Boot 3
- Apache Kafka
- MongoDB
- PostgreSQL
- Docker
- Redpanda Console
- Gradle

## Architecture

![Arquitetura Proposta](https://github.com/dev-Pedrod/saga-pattern/assets/86006066/68cbd65e-4ab7-4a28-98b5-e504bb0350d0)

## Run
Just run the file [build.py](https://github.com/dev-Pedrod/saga-pattern/blob/master/build.py). For that, you need to have Python 3 installed.

To execute, simply run the following command in the root directory of the repository:
```
py build.py
```
All applications will be built, all containers will be removed, and then `docker-compose` will be executed.

## The applications will run on the following ports:
```
- Order-Service: 3000
- Orchestrator-Service: 8080
- Product-Validation-Service: 8090
- Payment-Service: 8091
- Inventory-Service: 8092
- Apache Kafka: 9092
- Redpanda Console: 8081
- PostgreSQL (Product-DB): 5432
- PostgreSQL (Payment-DB): 5433
- PostgreSQL (Inventory-DB): 5434
- MongoDB (Order-DB): 27017
```

---
By dev-Pedrod  [*See my Linkedin*](https://www.linkedin.com/in/pedrooliveiradev/)
