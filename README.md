# PruebaTec4 - Sistema de Reservas de Vuelos y Hoteles

Este proyecto es una aplicación que permite gestionar reservas de vuelos y hoteles para una agencia de viajes. Proporciona endpoints RESTful para realizar operaciones como la creación, edición y eliminación de vuelos y hoteles, así como la realización y cancelación de reservas.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database (para desarrollo)
- Swagger (documentación de la API)
- JUnit (pruebas unitarias)

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/test/java`: Contiene las pruebas unitarias. Estas pruebas se encuentran en la clase HotelControllerTest
- `src/main/resources`: Contiene archivos de configuración y recursos entre los cuales se incluyen la base de datos en formato .sql y la Postman Collection.

  ### Jerarquía de paquetes
  La estructura de este proyecto sigue un modelo de capas:
  - Model: Contiene las entidades de las clases entidades.
  - Controller: Maneja las peticiones de los usuarios en cada endpoint.
  - Dto: Contiene clases DTO
  - Repository: Contiene interfaces con métodos que trabajan con la base de datos
  - Service: Alberga las clases que se encargan de la mayor parte lógica del programa así cómo de comunicarse con las capas repository y controller.
  - Config: Paquete de configuración que en este caso contiene la clase SecurityConfig encargada de la seguridad.
    
## Configuración

### Base de Datos

- La aplicación utiliza una base de datos H2 para desarrollo.
- Puedes configurar la conexión en `src/main/resources/application.properties`.
- Las credenciales predeterminadas para acceder a la base de datos en modo desarrollo son:
  - Usuario: `root`
  - Contraseña: `root`

### Seguridad

- La seguridad está habilitada mediante Spring Security.
- Puedes personalizar la configuración en `com.izambrana.pruebatec4.config.SecurityConfig`.
- Credenciales para endpoints con restricciones:
  - Usuario Administrador:
    - Usuario: `admin`
    - Contraseña: `admin`
      
## Cómo Ejecutar

1. Clona el repositorio: `git clone https://github.com/IvanZambrana/ZambranaIvan_pruebatec4`
2. Navega al directorio del proyecto: `cd pruebatec4`
3. Ejecuta la aplicación
4. Accede a la documentación Swagger: [http://localhost:8080/doc/swagger-ui.html](http://localhost:8080/doc/swagger-ui.html)
5. Usar Postman para probar los endpoints

## Endpoints Principales

- **Vuelos:**
  - Obtener todos los vuelos: `GET /agency/flights`
  - Crear nuevo vuelo: `POST /agency/flights/new`
  - Obtener información de un vuelo por ID: `GET /agency/flights/{id}`
  - Editar un vuelo: `PUT /agency/flights/edit/{id}`
  - Eliminar un vuelo: `DELETE /agency/flights/delete/{id}`
  - Reservar un vuelo: `POST /agency/flight-booking/new`
  - Cancelar reserva de vuelo: `DELETE /agency/flight-booking/delete/{id}`

- **Hoteles:**
  - Obtener todos los hoteles: `GET /agency/hotels`
  - Crear nuevo hotel: `POST /agency/hotels/new`
  - Obtener información de un hotel por ID: `GET /agency/hotels/{id}`
  - Editar un hotel: `PUT /agency/hotels/edit/{id}`
  - Eliminar un hotel: `DELETE /agency/hotels/delete/{id}`
  - Reservar un hotel: `POST /agency/hotel-booking/new`
  - Cancelar reserva de hotel: `DELETE /agency/hotel-booking/delete/{id}`

## Mejoras propuestas
Lista de posibles mejoras que se pueden implementar en un futuro:
  - Agregar endpoints para edición y listado de reservas.
  - Agregar más validaciones para que el usuario no pueda introducir en ningún momento valores erróneos.
  - Pruebas unitarias para cada caso.
  - Sistema de login y gestión de privilegios de usuarios.
