FROM openjdk:11
EXPOSE 8080
ADD target/payment-service.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]