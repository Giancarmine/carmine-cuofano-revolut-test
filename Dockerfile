FROM gradle:7-jdk11 AS build
ARG SECRET_KEY_BASE
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
ARG SECRET_KEY_BASE
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/
ENTRYPOINT ["java","-jar","/app/carmine-cuofano-revolut-test-all.jar"]