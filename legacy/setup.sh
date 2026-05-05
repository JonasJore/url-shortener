#!/bin/bash
echo "Setting up project"
docker-compose down
docker image rm url-shortener_api url-shortener-api
mvn clean install
mvn clean compile assembly:single
docker build -t url-shortener-api .
docker-compose up