# Stage 1: build with Maven (valid tag)
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy files and build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

RUN chmod +x mvnw && ./mvnw -B -DskipTests package

# Stage 2: runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /workspace/target/*.jar app.jar

ENV JAVA_OPTS=""
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
