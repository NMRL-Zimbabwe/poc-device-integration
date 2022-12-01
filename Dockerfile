FROM openjdk:11
EXPOSE 80
EXPOSE 8080
ADD ./target/poc-device-integration-0.0.1-SNAPSHOT.jar poc-device-integration-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/poc-device-integration-0.0.1-SNAPSHOT.jar"]
