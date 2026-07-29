# ==========================
# Build stage
# ==========================
FROM gradle:8.14.3-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle clean bootJar --no-daemon


# ==========================
# Runtime stage
# ==========================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENV SERVER_PORT=8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]