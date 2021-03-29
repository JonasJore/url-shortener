#!/bin/bash
echo "Setting up project"
mvn clean install
mvn clean compile assembly:single
docker build -t url-shortener-api .
docker-compose up