FROM openjdk:11-jdk
EXPOSE 8080:8080
RUN mkdir /app
CMD ./gradlew build
CMD ls -l
ENTRYPOINT ["java", "-cp", "/app/app.jar", "com.example.ApplicationKt"]