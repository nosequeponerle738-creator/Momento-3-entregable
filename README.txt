# Concesionario de Motos - Backend JPA & REST API

Aplicación backend desarrollada en Java con Spring Boot y JPA/Hibernate para la gestión integral de un concesionario de motos.

---

1. Configuración de Credenciales de Base de Datos

Para que el proyecto se conecte correctamente a tu base de datos relacional (por ejemplo, MySQL o PostgreSQL), debes configurar las credenciales en el archivo `src/main/resources/application.properties`:

```properties
# Configuración del servidor
server.port=8080

# Conexión a la Base de Datos (MySQL de ejemplo)
spring.datasource.url=jdbc:mysql://localhost:3306/concesionario_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña_segura

# Configuración de Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

Conexión Frontend y Backend
Backend: Procesa los datos de las motos (Motos y Kilometraje), calcula los promedios y aplica filtros de desgaste.
Frontend: Consume la información procesada y la muestra de forma visual en la interfaz del usuario.

¿Qué preguntas responde?
¿Cuál es el promedio de kilometraje y años de uso de todo el inventario?

R- Calculado mediante calcularPromedioKilometraje() y calcularPromedioAnosUso()

¿Qué motocicletas superan los límites de uso o antigüedad y requieren atención?

R-Identificadas mediante buscarMotosMuchoKilometraje() y buscarMotosConMuchosAnos()

¿Qué vehículos son nuevos y cuáles son de segunda mano?

R- Evaluado automáticamente con la regla de negocio esSegundaMano()

¿Qué aporta al proyecto?
Para el Administrador Permite tomar decisiones informadas sobre precios, control de inventario y detección de unidades con mayor desgaste
Para el Cliente: Ofrece total transparencia sobre el estado real, características y valor de cada motocicleta