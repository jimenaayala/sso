# Sistema SSO - Backend

Sistema de gestión de servicios de mantenimiento e inspección de equipos.

## Descripción

Este proyecto es una aplicación backend desarrollada con Spring Boot que proporciona una API RESTful para gestionar inspecciones, ensayos, mantenimiento y reparación de diferentes tipos de equipos (Cougar, DV1, MiniG, VH60). El sistema permite el seguimiento de procesos de recepción, inspección, ensayo y entrega de equipos para clientes.

## Características principales

- Gestión de clientes y sus equipos
- Seguimiento de órdenes de trabajo
- Procesos de recepción e inspección
- Ensayos específicos para cada tipo de equipo
- Generación de informes de salida
- Gestión de usuarios y control de acceso
- Almacenamiento y gestión de imágenes
- Limpieza automática de recursos antiguos

## Tecnologías utilizadas

- Java 8+
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Docker (para contenerización)

## Requisitos previos

- JDK 8 o superior
- Maven
- MySQL

## Configuración

La aplicación utiliza variables de entorno para la configuración de la base de datos:

- `DB_URL`: URL de conexión a la base de datos
- `DB_USER_NAME`: Usuario de la base de datos
- `DB_PASSWORD`: Contraseña de la base de datos

## Instalación y ejecución

1. Clonar el repositorio:
```bash
git clone [url-del-repositorio]
```

2. Navegar al directorio del proyecto:
```bash
cd SistemaSSO
```

3. Compilar el proyecto:
```bash
./mvnw clean package
```

4. Ejecutar la aplicación:
```bash
./mvnw spring-boot:run
```

También puedes ejecutar la aplicación usando Docker:
```bash
docker build -t sistema-sso .
docker run -p 8080:8080 -e DB_URL=jdbc:mysql://[host]:[puerto]/[bd] -e DB_USER_NAME=[usuario] -e DB_PASSWORD=[contraseña] sistema-sso
```

## Estructura del proyecto

- `controller`: Endpoints REST para acceso a la aplicación
- `entity`: Modelos de datos y entidades JPA
- `repository`: Interfaces para acceso a datos
- `service`: Lógica de negocio
- `config`: Configuraciones de la aplicación
- `exceptions`: Manejo de excepciones personalizado

## API Endpoints

El sistema proporciona endpoints para las siguientes entidades:

- `/api/clientes`: Gestión de clientes
- `/api/equipos`: Gestión de equipos
- `/api/ordenes`: Gestión de órdenes de trabajo
- `/api/recepciones`: Gestión de recepciones
- `/api/ensayos`: Gestión de ensayos (con variantes para cada tipo de equipo)
- `/api/inspecciones`: Gestión de inspecciones (con variantes para cada tipo de equipo)
- `/api/salidas`: Gestión de salidas/entregas
- `/api/users`: Gestión de usuarios

## Licencia

[Especificar licencia]
