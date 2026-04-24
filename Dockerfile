# 1-ci mərhələ: Build (Maven istifadə edərək JAR yaradılır)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2-ci mərhələ: Run (Yalnız JAR faylı işə salınır)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]