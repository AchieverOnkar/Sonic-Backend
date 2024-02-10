# Build Stage
FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Production Stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Sonic-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
