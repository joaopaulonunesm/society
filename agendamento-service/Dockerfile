FROM openjdk:11
ADD /target/*.jar agendamento-service.jar
ENTRYPOINT ["java", "-jar", "agendamento-service.jar"]
EXPOSE 8080