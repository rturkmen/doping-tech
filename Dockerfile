FROM openjdk:21-jdk-slim
VOLUME /tech-0.0.1-SNAPSHOT
ARG JAR_FILE=target/tech-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} tech-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/tech-0.0.1-SNAPSHOT.jar"]