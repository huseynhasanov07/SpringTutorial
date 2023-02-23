FROM --platform=linux/amd64 openjdk:17-jdk-alpine

EXPOSE 8080

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","/usr/src/app/demo-0.0.1-SNAPSHOT.jar"]