FROM eclipse-temurin:17-jdk-alpine as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN apk add --update dos2unix
RUN dos2unix ./mvnw

RUN ./mvnw -B -Dmaven.test.skip package

FROM eclipse-temurin:17-jre-alpine

COPY --from=build target/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]