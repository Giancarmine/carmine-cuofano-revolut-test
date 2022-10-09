FROM gradle:jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11-jdk
EXPOSE 8080:8080

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/ /app/

ENTRYPOINT ["java","-jar","/app/carmine-cuofano-revolut-test-0.0.1.jar", "com.example.ApplicationKt"]