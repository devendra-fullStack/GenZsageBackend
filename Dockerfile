# ================================
# 1) Build Stage – Maven builds the JAR
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests


# ================================
# 2) Runtime Stage – Copy ONLY the real Boot JAR
# ================================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy only the runnable Spring Boot jar (exclude .original)


COPY --from=build /app/target/*napshot.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

