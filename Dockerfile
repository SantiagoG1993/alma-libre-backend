FROM gradle:7.3.0-jdk11 AS build
WORKDIR /app
COPY . /app
RUN gradle clean build


FROM amazoncorretto:11
EXPOSE 8080
RUN mkdir -p /app/
COPY --from=build /app/build/eCommerce-0.0.1-SNAPSHOT.jar /app/eCommerce-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar","/app/eCommerce-0.0.1-SNAPSHOT.jar"]