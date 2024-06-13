FROM openjdk:17

WORKDIR /app

COPY target/mail-service-0.0.1-SNAPSHOT.jar .

EXPOSE 5200

CMD ["java", "-jar", "mail-service-0.0.1-SNAPSHOT.jar"]