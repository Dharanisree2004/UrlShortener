FROM openjdk:21-jdk-slim
WORKDIR /app
# Copy the Spring Boot JAR file into the container
COPY target/UrlShortener-0.0.1-SNAPSHOT.jar app.jar
# Start he Spring Boot application directly
ENTRYPOINT ["java", "-jar", "app.jar"]
