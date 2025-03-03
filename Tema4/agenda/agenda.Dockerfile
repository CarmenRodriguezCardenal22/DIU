FROM openjdk:21
LABEL authors="carmen"

COPY target/Agenda-0.0.1-SNAPSHOT.jar /agendaapp2.jar
CMD ["java", "-jar", "/agendasapp2.jar"]