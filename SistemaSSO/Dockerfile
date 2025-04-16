# Etapa de construcción
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
# Descargar todas las dependencias para aprovechar la caché de Docker
RUN mvn dependency:go-offline -B
COPY src ./src
# Empaquetar la aplicación omitiendo las pruebas para acelerar el proceso
RUN mvn package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Variables de entorno (se pueden sobrescribir al ejecutar el contenedor)
ENV DB_URL=jdbc:mysql://mysql:3306/sso_db
ENV DB_USER_NAME=root
ENV DB_PASSWORD=root
ENV TZ=America/Argentina/Buenos_Aires

# Copiar el JAR compilado desde la etapa de construcción
COPY --from=build /app/target/SistemaSSO-*.jar /app/sistema-sso.jar

# Puerto que expone la aplicación
EXPOSE 8080

# Configuración de JVM para rendimiento en contenedor
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "/app/sistema-sso.jar"]
