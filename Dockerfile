FROM openjdk:11-jdk
EXPOSE 8080:8080

VOLUME /tmp

RUN mkdir /app
CMD ./gradlew build

COPY ./build/libs/carmine-cuofano-revolut-test-all.jar /app/app.jar
ENTRYPOINT ["java", "-cp", "/app/app.jar", "com.example.ApplicationKt"]