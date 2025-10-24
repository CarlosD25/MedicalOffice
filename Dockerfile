FROM maven:3.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine
VOLUME /tmp
COPY --from=build /app/target/*.jar medicaloffice.jar
ENTRYPOINT ["java", "-jar", "/medicaloffice.jar"]