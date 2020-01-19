 FROM openjdk:8-jdk-alpine

COPY ./tmp/chillout-server-1.0.0.jar chillout-server.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","chillout-server.jar"]
