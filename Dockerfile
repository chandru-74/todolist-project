# Use official Maven + JDK image for build stage
FROM maven:3.8.6-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Use lightweight JDK image for runtime
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
