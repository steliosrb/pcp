FROM openjdk:8
EXPOSE 8282
ADD target/consumer-microservice.jar consumer-microservice.jar
ENTRYPOINT ["java","-jar","/consumer-microservice.jar"]