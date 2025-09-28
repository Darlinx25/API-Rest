#!/bin/bash
# Compilar con Maven y crear imagen Docker
./mvnw clean package -DskipTests

# Construir y levantar con docker-compose
docker-compose up --build
