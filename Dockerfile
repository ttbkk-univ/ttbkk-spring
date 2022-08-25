FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY gradle /app/gradle
COPY src /app/src
COPY build.gradle /app/build.gradle
COPY gradlew /app/gradlew
COPY gradlew.bat /app/gradlew.bat
COPY settings.gradle /app/settings.gradle

RUN ./gradlew build

COPY .env /app/.env

ENTRYPOINT ["java", "-jar", "./build/libs/api-0.0.1-SNAPSHOT.jar"]