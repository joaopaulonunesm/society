FROM openjdk:11
ADD /target/*.jar society-service.jar
ENTRYPOINT ["java", "-jar", "society-service.jar"]
EXPOSE 8080