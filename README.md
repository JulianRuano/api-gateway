# API Gateway

Esta es una API Gateway construida con Spring Cloud Gateway que actúa como punto de entrada único para las solicitudes a los microservicios de la aplicación. Utiliza el equilibrio de carga (load balancing) para distribuir el tráfico entre las instancias de cada microservicio.

## Requisitos

- Docker
- Docker Compose

## Configuración

La API Gateway está configurada para enrutar las solicitudes a tres microservicios diferentes:

1. **Product Microservice**: Maneja las solicitudes relacionadas con productos. Todas las rutas que comiencen con `/product/**` serán enrutadas a este microservicio.

2. **Booking Microservice**: Maneja las solicitudes relacionadas con reservas. Todas las rutas que comiencen con `/booking/**` serán enrutadas a este microservicio.

3. **File Microservice**: Maneja las solicitudes relacionadas con archivos configurando un servidor nginx para exponer los archivos. Todas las rutas que comiencen con `/file/**` serán enrutadas a este microservicio.

La configuración de enrutamiento se encuentra en el archivo `application.properties`

## Ejecución

Para ejecutar la API Gateway, simplemente ejecuta el siguiente comando desde la raíz del proyecto:
```
docker-compose build
```
La API Gateway estará disponible en `http://localhost:8080`.

## Uso

Una vez que la API Gateway esté en funcionamiento, puedes enviar solicitudes HTTP a `http://localhost:8080` seguidas de la ruta correspondiente al microservicio que deseas acceder. Por ejemplo:

- `http://localhost:8080/product/` 
- `http://localhost:8080/booking/` 
- `http://localhost:8080/file/` 
