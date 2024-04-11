# Schwarz IT BOGY Exercise Task

### Overview

This is a small and simple BOGY Application. Its purpose is to produce content for 
Electronic Shelf Labels (ESLs). Intentionally, it supports only a minimal feature set. This comprises:

* Input ESL Params as fixed values or by a restful request
* Output ESL Content as PNG to a File or to a Restful-Endpoint 


### Compile

You need JDK 21 (or later) and optionally [Apache Maven](https://maven.apache.org/) to compile and run the code.

#### Compile Option 1: with Maven Wrapper (no need to install Maven)
`./mvnw clean package`

#### Compile Option 2: with Maven installed on your machine
`mvn clean package`

### Run
To run the application you should set the according profile of the environment.

#### IDE
Set the profile to <your-profile> in the settings of your IDE.

#### Development Environment
`java -jar -Dspring.profiles.active=<dev-profile> ./target/ld2i-0.0.1-SNAPSHOT.jar`


