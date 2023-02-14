FROM openjdk:17-alpine
EXPOSE 8080
ADD build/libs/quotes_rest_api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "quotes_rest_api-0.0.1-SNAPSHOT.jar"]