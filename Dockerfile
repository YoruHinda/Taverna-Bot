FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM maven:3.6.0-jdk-11-slimq
COPY --from=build /app/target/TavernaBot-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar
ENTRYPOINT exec java -jar /app/app.jar