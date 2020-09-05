## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Logging](#logging)

## General info
This project is a challenge to build a program that implements the following mower specification.
	
## Technologies
Project is created with:
* JDK version: 11.0.5
* Maven Plugin version: 3.6.3
	
## Setup
To run this project, by default the file located on src/main/resources/laxn.twt will be used as input. Install it locally using the following git cli commands:

```
$ git clone https://github.com/ESegundoRolon/mow-app.git
$ cd mow-app
$ mvn clean install
$ java -jar ./target/mow-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

To achieve simulation using custom file, pass the file path as parameter. For example given the file file:/C:/System/example2.txt:

```
$ git clone https://github.com/ESegundoRolon/mow-app.git
$ cd mow-app
$ mvn clean install
$ java -jar ./target/mow-app-1.0-SNAPSHOT-jar-with-dependencies.jar file:/C:/System/example2.txt
```

If the given input file has errors, you will see an output message as follows:

```
Exception in thread "main" com.mow.app.exception.InvalidFileFormatException: Mowers should not be overlapping
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

## Logging

By default there is only console logging activated. In order to activate the file appender, the log4j.properties file located on src/main/resources should be configured as follows:

```
# Root logger option
log4j.rootLogger=DEBUG, stdout, file

# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %t %-5p %c{1}:%L - %m%n

# Redirect log messages to a log file, support file rolling.
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.append=false
log4j.appender.file.File=C:\\log4j-mow-application.log
log4j.appender.file.MaxFileSize=5MB
log4j.appender.file.MaxBackupIndex=10
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %t %-5p %c{1}:%L - %m%n
```
