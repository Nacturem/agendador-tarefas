FROM gradle:7.5-jdk17 as build
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/demo.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/demo.jar"]
