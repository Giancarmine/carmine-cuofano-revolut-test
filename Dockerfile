FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*.jar /app/carmine-cuofano-revolut-test-0.0.1.jar
ENTRYPOINT ["java","-jar","/app/carmine-cuofano-revolut-test-0.0.1.jar"]