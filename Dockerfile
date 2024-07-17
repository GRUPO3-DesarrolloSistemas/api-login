FROM openjdk:17-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/api-login-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} api-login.jar
ENTRYPOINT ["java","-jar","/api-login.jar"]