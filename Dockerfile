# Step 1: Build the application using Maven
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -e -B -DskipTests package

# Step 2: Run the application
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

