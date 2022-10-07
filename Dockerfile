FROM openjdk:11-jdk
EXPOSE 8080:8080
RUN mkdir /app
CMD ./gradlew build
COPY ./build/libs/*.jar \
    /usr/local/app/
ENTRYPOINT ["java", "-cp", "/app/app.jar", "com.example.ApplicationKt"]