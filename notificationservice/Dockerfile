FROM gradle:8.14-jdk21 AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/

RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle dependencies --no-daemon || true

COPY src/ src/

RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]