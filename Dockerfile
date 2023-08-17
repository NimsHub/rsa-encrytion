FROM openjdk:17-alpine
LABEL authors="nims"
COPY target/rsa-encryption-1.0.jar app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar", "app.jar"]