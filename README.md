## Features

* This project reads a csv file, adds to a H2 database and finds the producers with the smallest interval
between two wins and the biggest interval between two wins (following specifications 
in the [Especificação Backend.pdf](Especifica%C3%A7%C3%A3o%20Backend.pdf) file).

# Instructions
* To run the project you must have **Java 11** and **Maven** installed on your computer.
* You can run the integration tests and the project by importing the project on an IDE (IntelliJ was used during the development phase)
* To download dependencies and run integration tests using command line, you must use maven:
`mvn clean install`
* To run the project using command line, you must use maven: `mvn spring-boot:run`

* To test the endpoint, you can use the following CURL, either directly through command line or with Postman:
  `curl --location --request GET 'http://localhost:8080/texo/producers/biggest-smallest-winning-interval'`
