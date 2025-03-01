FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-21-jdk maven

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

EXPOSE 9090

COPY --from=build /app/target/avanade-dio-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]