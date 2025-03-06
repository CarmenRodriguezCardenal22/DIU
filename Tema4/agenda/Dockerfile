# Usamos una imagen base con OpenJDK
FROM openjdk:23-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el archivo JAR del proyecto en el contenedor
COPY target/agenda-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto que va a escuchar la aplicación
EXPOSE 8099

# Comando para ejecutar la aplicación Java
ENTRYPOINT ["java", "-jar", "app.jar"]
