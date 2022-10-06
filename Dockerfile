FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*.jar /app/
ENTRYPOINT ["java", "-cp", "/app/carmine-cuofano-revolut-test-all.jar", "com.example.ApplicationKt"]