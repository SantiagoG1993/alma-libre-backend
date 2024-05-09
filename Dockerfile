FROM gradle:7.3.0-jdk11-alpine

COPY . .


RUN gradle clean package build


EXPOSE 8080

ENTRYPOINT ["java", "-jar","/build/libs/eCommerce-0.0.1-SNAPSHOT.jar"]