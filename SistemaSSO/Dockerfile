# Utiliza una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR a la imagen del contenedor
COPY target/SistemaSSO-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto 8080 que usará la aplicación
EXPOSE 8080

# Ejecuta el archivo JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
