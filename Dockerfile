# --- Stage 1: Build Stage ---
FROM gradle:8.5-jdk21-alpine AS builder

WORKDIR /demo

# Copy Gradle wrapper and configuration files first (caches dependencies)
COPY gradle ./gradle
COPY gradlew build.gradle settings.gradle ./
RUN ./gradlew dependencies --no-daemon

# Copy source files and build the executable bootJar
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# --- Stage 2: Production Runtime Stage ---
FROM azul/zulu-openjdk-alpine:21-jre-headless

WORKDIR /app

# Copy the compiled JAR from Stage 1 using standard wildcard matching
COPY --from=builder /demo/build/libs/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]