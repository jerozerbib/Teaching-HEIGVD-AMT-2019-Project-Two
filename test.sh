#!/bin/bash
rm -rf ./docker-images/user-manager-server/tmp
mkdir ./docker-images/user-manager-server/tmp

mvn clean package -f services/UserManagement/user-manager-server/pom.xml

cp services/UserManagement/user-manager-server/target/user-manager-server-1.0.0.jar docker-images/user-manager-server/tmp/

docker build -t user_api ./docker-images/user-manager-server/

docker-compose -f ./docker-topologies/docker-compose.yml down -v
docker-compose -f docker-topologies/docker-compose.yml up --build user_api_db &

sleep 15

docker-compose -f docker-topologies/docker-compose.yml up -d --build user_api

sleep 10
cd services/UserManagement/user-manager-specs/ && mvn test

