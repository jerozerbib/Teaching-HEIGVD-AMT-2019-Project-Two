#!/bin/bash
rm -r ../docker-images/user-manager-server/tmp
mkdir ../docker-images/chillout-server/tmp
mkdir ../docker-images/user-manager-server/tmp

mvn clean package -f ../services/UserManagement/user-manager-server/pom.xml
mvn clean package -f ../services/Chill/chillout-server/pom.xml

cp ../services/UserManagement/user-manager-server/target/user-manager-server-1.0.0.jar ../docker-images/user-manager-server/tmp/

cp ../services/Chill/chillout-server/target/chillout-server-1.0.0.jar ../docker-images/chillout-server/tmp/


docker build -t user_api ../docker-images/user-manager-server/
docker build -t chillout_api ../docker-images/chillout-server/

docker-compose down -v
docker-compose up -d --build

sleep 30

docker-compose up --build phpmyadmin user_api chillout_api
