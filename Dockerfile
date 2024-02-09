FROM openjdk:21-jdk-slim as build
EXPOSE 8080
COPY target/Sonic-0.0.1-SNAPSHOT.jar app.jar
CMD [ "java","-jar","app.jar" ]
