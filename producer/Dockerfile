FROM openjdk:8
EXPOSE 8181
ADD target/producer-microservice.jar producer-microservice.jar
ENTRYPOINT ["java","-jar","/producer-microservice.jar"]