FROM openjdk:8
EXPOSE 8080
ADD target/queue-microservice.jar queue-microservice.jar
ENTRYPOINT ["java","-jar","/queue-microservice.jar"]