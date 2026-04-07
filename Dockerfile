FROM maven:4.0.0-rc-5-eclipse-temurin-21-alpine AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine-3.23
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/technologie-backendowe-projekt-0.0.1-SNAPSHOT.jar /app/java-application.jar
RUN chown -R javauser:javauser /app
WORKDIR /app
USER javauser
EXPOSE 8080
CMD "java" "-jar" "java-application.jar"