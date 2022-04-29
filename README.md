# payment-service
A service intended to handle payment method related operations along with payment plans.


## Clone a repository ##
Go to link https://github.com/wasimwaheed07146/payment-service

And clone by using following command
- git clone https://github.com/wasimwaheed07146/payment-service.git

## How to run an application ##


## As a standalone springboot app ##

Go to payment-service folder and run the following commands

- mvn clean install

Above command will create a jar file name "payment-service.jar" in payment-service/target directory

Now run the jar using following command
- java -jar payment-service.jar


## Using docker ##
Go to the directory of Dockerfile i.e in "payment-service" folder in repository

Build docker image using following command

- docker build -t starzplay-payment-service .

Run the docker image using following command
- docker run -p 8080:8080 starzplay-payment-service

## Swagger URL ##

You can view all the API's used in application on following url

- http://localhost:8080/swagger-ui.html


## H2 Database Console URL ##

- http://localhost:8080/h2-console

- jdbc url : jdbc:h2:mem:starzplay
- username : root
- password : root,

Note : H2Console is unable to access from inside docker container. Unable to fix due to time shortage


## SQL Script ##

- I have used liquibase to create database schema
- Scripts are present in "db.changelog-master.xml" file present in payment-service/src/main/resources directory

## Input Payload ##

Added an exported postman collection for reference of input and output payload

- /payment-service/starzplay-paymentApi-collection.json

Import the above file in postman for better readability

## Language, Frameworks, Libraries ##

* Java 11
* Spring Boot 2.6.7
* Spring Web
* Spring Data JPA
* Lombok
* Mapstruct
* Mockito
* H2 Database
* Liquibase
* Springdoc
