# ===== Stage 1: Build the app =====
FROM maven:3.9.9-eclipse-temurin-22 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: Run the app =====
FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app

# Copy only the JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8050
ENTRYPOINT ["java", "-jar", "app.jar"]
