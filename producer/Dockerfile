FROM openjdk:11-jdk

EXPOSE 8080

ADD build/libs/producer-0.0.1-SNAPSHOT.jar producer.jar

ENTRYPOINT java -jar producer.jar