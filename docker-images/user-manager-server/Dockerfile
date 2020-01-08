 FROM openjdk:8-jdk-alpine

COPY ./tmp/user-manager-server-1.0.0.jar user-manager-server.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","user-manager-server.jar"]
