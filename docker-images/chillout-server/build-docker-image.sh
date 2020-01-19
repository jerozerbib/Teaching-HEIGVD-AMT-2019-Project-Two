#!/bin/bash

rm -r tmp
mkdir tmp

mvn clean package -f ../../services/Chill/chillout-server/pom.xml
cp ../../services/Chill/chillout-server/target/chillout-server-1.0.0.jar ./tmp/

docker build -t chillout_api .
