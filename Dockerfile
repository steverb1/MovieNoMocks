FROM openjdk:17
ADD target/movie-api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
