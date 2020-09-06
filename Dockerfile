# select parent image
FROM maven:3.6.3-jdk-11

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean package

# set the startup command to execute the jar
CMD ["java", "-jar", "target/mow-app-1.0-SNAPSHOT-jar-with-dependencies.jar"]