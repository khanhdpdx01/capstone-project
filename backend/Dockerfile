FROM openjdk:11-jdk-slim-bullseye
LABEL maintainer="khanhdpdx@gmail.com"
EXPOSE 3000
ENV LANG C.UTF-8
ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]
