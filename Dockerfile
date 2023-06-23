FROM openjdk:17-jdk-alpine
EXPOSE 8080
RUN apk update && apk add mysql-client
COPY target/music-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]