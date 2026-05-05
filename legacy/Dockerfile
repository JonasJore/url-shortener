FROM openjdk:14-jdk-alpine
RUN addgroup -S url-shortener-group && adduser -S url-shortener-user -G url-shortener-group
USER url-shortener-user:url-shortener-group
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]