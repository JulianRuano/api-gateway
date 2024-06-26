# API Gateway

Esta es una API Gateway construida con Spring Cloud Gateway que actúa como punto de entrada único para las solicitudes a los microservicios de la aplicación. Utiliza un balanceador de carga para distribuir el tráfico entre las instancias de cada microservicio.

Configura un contenedor de NGINX utilizando Docker Compose. El contenedor de NGINX está configurado para servir archivos estáticos (imágenes) desde un volumen de Docker, y expone el servidor web en el puerto 8085 de la máquina host.

![](https://res.cloudinary.com/dilrruxyx/image/upload/v1719362272/Untitled-2024-06-25-1533_vefchr.svg)

## Requisitos

- Docker
- Docker Compose

## Configuración

La API Gateway está configurada para enrutar las solicitudes a tres microservicios diferentes:

1. **Product Microservice**: Todas las rutas que comiencen con `/product/**` serán enrutadas a este microservicio.

2. **Booking Microservice**: Todas las rutas que comiencen con `/booking/**` serán enrutadas a este microservicio.

3. **Stock-microservice**: Todas las rutas que comiencen con `/stock/**` serán enrutadas a este microservicio.

3. **File Microservice**: Todas las rutas que comiencen con `/file/**` serán enrutadas a este microservicio.

La configuración de enrutamiento se encuentra en el archivo `application.properties` en la carpeta `api-gateway`

### Configuración del archivo .env
El contenido del archivo .env debe incluir las siguientes variables ejemplo:

```
MARIADB_USER= root
MARIADB_PASSWORD= root
MARIADB_ROOT_PASSWORD= root

DB_URL_BOOKING=jdbc:mariadb://db_booking:3306/db_booking
DB_URL_PRODUCT=jdbc:mariadb://db_product:3306/db_product
DB_URL_STOCK=jdbc:mariadb://db_stock:3306/db_stock
```

El archivo `docker-compose.yml` utiliza las variables definidas en el archivo .env y configura los servicios:

## Ejecución

Para ejecutar la API Gateway, simplemente ejecuta el siguiente comando desde la raíz del proyecto:

#### Construir los servicios:
```
docker compose build
```
#### Levantar los servicios:
```
docker compose up
```

La API Gateway estará disponible en `http://localhost:8080`.

## Uso

Una vez que la API Gateway esté en funcionamiento, puedes enviar solicitudes HTTP a `http://localhost:8080` seguidas de la ruta correspondiente al microservicio que deseas acceder. Por ejemplo:

- `http://localhost:8080/product/` 
- `http://localhost:8080/booking/` 
- `http://localhost:8080/stock/` 
- `http://localhost:8080/file/` 


![](https://res.cloudinary.com/dilrruxyx/image/upload/v1719363738/Captura_desde_2024-06-25_20-01-24_yp5i5i.png)

