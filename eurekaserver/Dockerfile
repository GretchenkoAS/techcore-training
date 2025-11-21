FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src/ src/

RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]