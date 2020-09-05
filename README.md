## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is a challenge to build a program that implements the following mower specification.
	
## Technologies
Project is created with:
* JDK version: 11.0.5
* Maven Plugin version: 3.6.3
	
## Setup
To run this project, install it locally using git cli command:

```
$ git clone https://github.com/ESegundoRolon/mow-app.git
$ cd mow-app
$ mvn clean install
$ java -jar ./target/mow-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The unit test are run while the application is packaged using the maven install command. To run only unit test you must run the following commands:

```
$ git clone https://github.com/ESegundoRolon/mow-app.git
$ cd mow-app
$ mvn clean test -P dev
```

In order to run the integration test, use the following commands:

```
$ git clone https://github.com/ESegundoRolon/mow-app.git
$ cd mow-app
$ mvn clean verify -P integration-test
```