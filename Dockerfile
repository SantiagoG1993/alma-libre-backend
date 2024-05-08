FROM gradle:7.3.0-jdk11-alpine

COPY . .

EXPOSE 8080

RUN gradle clean build



ENTRYPOINT ["java", "-jar","/build/libs/eCommerce-0.0.1-SNAPSHOT.jar"]