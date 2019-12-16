#!/bin/bash
rm -r ../docker-images/user-manager-server/tmp
mkdir ../docker-images/user-manager-server/tmp

mvn clean package -f ../services/UserManagement/user-manager-server/pom.xml
cp ../services/UserManagement/user-manager-server/target/user-manager-server*.jar ./tmp/

docker build -t user-manager/user-manager-server ../docker-images/user-manager-server/

docker-compose down
docker-compose up -d --build mysql

sleep 20

docker-compose up --build phpmyadmin user-manager
