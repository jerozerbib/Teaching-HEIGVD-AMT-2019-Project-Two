#!/bin/bash

rm -r tmp
mkdir tmp

mvn clean package -f ../../services/UserManagement/user-manager-server/pom.xml
cp ../../services/UserManagement/user-manager-server/target/user-manager-server*.jar ./tmp/

docker build -t user-manager/user-manager-server .
