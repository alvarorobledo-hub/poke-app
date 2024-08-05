# poke-app

poke-app es una aplicación basada en Spring Boot que interactúa con una API externa de Pokémon, almacena datos en Redis y utiliza diversas herramientas para asegurar la calidad del código, como SonarQube.

## Tabla de Contenidos

1. [Requisitos](#requisitos)
2. [Configuración del Proyecto](#configuración-del-proyecto)
3. [Ejecutar Localmente](#ejecutar-localmente)
4. [Arquitectura](#arquitectura)
5. [Mejoras Implementadas](#mejoras-implementadas)
6. [Mejoras Futuras](#mejoras-futuras)

## Requisitos

- **Java**: 17
- **Gradle**: 7.5

## Configuración del Proyecto

### Variables de Entorno

Asegúrese de configurar las siguientes variables de entorno:

- `SPRING_PROFILES_ACTIVE`: Define el perfil activo de Spring (e.g., `dev`, `pro`).

### Dependencias Principales

```groovy
dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // docs
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${openapiVersion}")

    // redis
    implementation("org.redisson:redisson:${redisVersion}")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
```

## Ejecutar Localmente

### Paso 1: Clonar el Repositorio

```sh
https://github.com/alvarorobledo-hub/poke-app.git
cd poke-app
```

### Paso 2: Construir el Proyecto

```sh
./gradlew clean build
```

### Paso 3: Ejecutar la Aplicación con Docker Compose

```sh
docker-compose up -d
```

### Paso Extra: Ejecutar SonarQube si se requiere
```sh
./gradlew sonar
```

---
La aplicación estará disponible en http://localhost:8080 y http://localhost:8080/swagger-ui/index.html. 
Previamente se ha subido la imagen a docker-hub https://hub.docker.com/repository/docker/alvarorobledo/pokeapp/general con el tag 1.0.0 y latest.

## Arquitectura
La aplicación sigue una arquitectura hexagonal, que está diseñada para separar las responsabilidades y facilitar el mantenimiento, la escalabilidad y la prueba del sistema. Las tres capas principales de la arquitectura son: Infraestructura, Aplicación y Dominio.

### Capa de Infraestructura
La **capa de infraestructura** es responsable de la comunicación con sistemas externos y de la persistencia de datos. Proporciona las implementaciones concretas de los servicios necesarios para el funcionamiento de la aplicación.

- **`client/PokeApiClient`**: Adaptador que utiliza la interfaz `PokemonClient` para realizar llamadas a una API externa de Pokémon. Implementa el método `getAllPokemon` para obtener una lista de Pokémon, manejando la paginación y el procesamiento de resultados.
- **`client/PokeApiClientDefinition`**: Se decidió utilizar Feign porque proporciona una forma más declarativa de realizar llamadas HTTP y simplificandolas, ya que se trata de una interfaz. También reduce código boilerplate y facilita las pruebas con el mockeo de clientes.
- **`api`**
- **`api/handler`**
- **`config`**
- **`dto`**: Se decidió añadir esta carpeta en la parte de infrastructura, debido a que los objetos de transferencia pertenecen al cliente externo `PokeApiClient`. 
- **`mapper`**: Se decidió añadir esta carpeta en la parte de infrastructura, debido a que los objetos a mapear pertenecen al cliente externo `PokeApiClient`. 
- **`persistence/RedisPokemonRepository`**: Esta clase es responsable de la persistencia de datos en Redis. Implementa la interfaz `PokemonRepository` y se encarga de guardar y recuperar objetos `Pokemon` en la base de datos Redis

### Capa de Aplicación
La **capa de aplicación** se encarga de coordinar la lógica de negocio de la aplicación y de orquestar las interacciones entre la capa de dominio y la capa de infraestructura.

- **`service`**
- **`strategy/retry/BackoffRetryer`**: Implementa la interfaz `Retryer` para gestionar los reintentos en caso de fallos en las llamadas a servicios externos. Utiliza un enfoque de retroceso exponencial para manejar errores temporales de manera efectiva.
- **`strategy/decoder/FeignErrorDecoder`**: Su función es manejar errores durante las llamadas a APIs y convertirlos en excepciones específicas.
- **`utils`**

### Capa de Dominio
La **capa de dominio** es el núcleo de la lógica de negocio. Define las entidades, las reglas de negocio y las interfaces que son independientes de la infraestructura y la capa de aplicación.

- **`model/Pokemon`**: Representa la entidad central de la aplicación, que encapsula la información y las reglas relacionadas con los Pokémon. Incluye atributos como `id`, `name`, `weight`, `height` y `base_experience`.
- **`model/PokemonError`**: Representa la entidad de error de la aplicación, que encapsula la información y las reglas relacionadas de los errores producidos. Incluye atributos como `message` y `pokeStatus`.
- **`client/PokemonClient`**: Interfaz para abstraer el acceso a los datos. Define el contrato para recuperar información de una entidad externa.
- **`repository/PokemonRepository`**: Interfaz para abstraer el acceso a los datos. Define el contrato para almacenar y recuperar objetos `Pokemon` de un repositorio.
- **`exceptions/PokemonNotFound`**: Excepción personalizada lanzada cuando un Pokémon no es encontrado en la API o en el repositorio. Se utiliza para indicar errores específicos relacionados con la búsqueda de Pokémon.
- **`exceptions/PokemonServerError`**: Excepción general para manejar errores del servidor y situaciones inesperadas. Se utiliza para encapsular errores que no encajan en categorías específicas como `PokemonNotFound`.


## Mejoras implementadas

### Uso de Redis para el Almacenamiento en Caché
Se ha integrado Redis como sistema de almacenamiento en caché para mejorar el rendimiento de la aplicación. Redis se utiliza para almacenar la información de los Pokémon, permitiendo una recuperación rápida y eficiente de datos sin necesidad de realizar constantes solicitudes a la fuente de datos original. Esta integración tiene varias ventajas:

- **Reducción del tiempo de respuesta**: Almacenar en caché los datos de los Pokémon reduce el tiempo necesario para recuperarlos, mejorando la experiencia del usuario.
- **Menor carga en el sistema fuente**: Al evitar solicitudes repetidas a la fuente de datos, Redis ayuda a reducir la carga en el sistema externo.
- **Escalabilidad**: Redis es conocido por su alto rendimiento y escalabilidad, lo que es ideal para aplicaciones con altos volúmenes de datos y tráfico.

### Integración de Feign Client con Retryer y Error Decoder
**Feign Client** se ha utilizado para simplificar la comunicación con servicios externos. Junto con Feign, se ha implementado un `Retryer` y un `Error Decoder` para manejar las solicitudes fallidas y errores de manera robusta:

- **BackoffRetryer**: El `Retryer` se encarga de reintentar solicitudes fallidas de forma exponencial con un retraso entre intentos. Esto es crucial para manejar fallos temporales en la comunicación con servicios externos y mejorar la resiliencia de la aplicación.
- **FeignErrorDecoder**: El `Error Decoder` se encarga de interpretar los errores devueltos por el servicio externo. Permite mapear códigos de error específicos a excepciones personalizadas:

    - **`PokemonNotFound`**: Para manejar el caso en que un recurso no se encuentra (por ejemplo, un Pokémon específico no está disponible).
    - **`PokemonServerError`**: Para manejar errores generales del servidor, proporcionando una respuesta adecuada en caso de fallos en el servicio externo.

### Uso de SonarQube
Se ha configurado **SonarQube** para el análisis de calidad del código y la gestión de la deuda técnica. Su integración ayuda a mantener un código limpio y conforme a las mejores prácticas, mejorando la calidad general del software.

### Documentación con Swagger
Se ha integrado **Swagger** para proporcionar una documentación interactiva de la API. Swagger facilita a los desarrolladores explorar y entender los endpoints disponibles en la API de manera visual y accesible. Las ventajas incluyen:

- **Interactividad**: Los desarrolladores pueden probar directamente las llamadas a la API desde la interfaz Swagger.
- **Documentación clara**: Proporciona una descripción clara de los endpoints, parámetros y posibles respuestas, mejorando la comprensión de la API.

### Monitorización con Actuator
**Spring Boot Actuator** se ha añadido para proporcionar funcionalidades de monitorización y gestión dentro de la aplicación. Actuator proporciona varios puntos finales de gestión que permiten:

- **Estado de la aplicación**: Verificar el estado y la disponibilidad de la aplicación.
- **Métricas**: Obtener métricas sobre el rendimiento y el estado de la aplicación.
- **Salud**: Monitorear el estado de salud de los componentes y servicios externos integrados en la aplicación.

Estas características ayudan a mantener un control adecuado sobre la operación de la aplicación y facilitan la identificación y resolución de problemas.

---
Estas mejoras han sido implementadas para asegurar que la aplicación sea más eficiente, robusta y fácil de mantener. Cada componente añadido contribuye a una mejor experiencia del usuario y una operación más fiable del sistema en su conjunto.


## Mejoras futuras

### Pipeline para Despliegues Automáticos
Implementar una **pipeline de integración continua (CI) y despliegue continuo (CD)** sería una mejora significativa para automatizar el proceso de despliegue. Una pipeline bien configurada permitiría:

- **Despliegue Automatizado**: Automatizar el despliegue de nuevas versiones de la aplicación en entornos de `dev` y `pro`, reduciendo la necesidad de intervención manual.
- **Pruebas Automatizadas**: Ejecutar pruebas unitarias, de integración y de aceptación como parte del proceso de construcción y despliegue para asegurar que los cambios no rompan la funcionalidad existente.
- **Revisión de Código**: Integrar revisiones de código y análisis de calidad para mantener un alto estándar en el código fuente, como por ejemplo, el 90% de cobertura.

### Integración de JaCoCo en SonarQube

**JaCoCo** se utiliza para la cobertura de pruebas en la aplicación, y su integración con **SonarQube** permitiría obtener métricas de cobertura de código directamente en el informe de SonarQube. Sin embargo, actualmente existe un problema conocido con la configuración de JaCoCo que impide ejecutar `gradlew clean build` correctamente. Las mejoras futuras incluyen:
- **Cobertura de Código**: Integrar informes de cobertura de código en SonarQube para obtener una visión detallada de la cobertura de pruebas y la calidad del código.

### Implementación de Autenticación
Aunque en la versión actual no se ha implementado autenticación, agregar un sistema de **autenticación y autorización** podría mejorar la seguridad de la aplicación. Las posibles mejoras incluyen:

- **Autenticación de Usuario**: Implementar un sistema de autenticación basado en JWT o OAuth2 para proteger los endpoints de la API y controlar el acceso a recursos específicos.
- **Autorización Basada en Roles**: Definir roles y permisos para gestionar el acceso a diferentes partes de la aplicación.

### Monitorización con Grafana
Integrar **Grafana** para la monitorización de recursos y métricas de rendimiento de la aplicación sería una adición valiosa. Grafana permite visualizar datos en tiempo real y monitorear recursos como CPU y memoria. Las mejoras futuras podrían incluir:

- **Visualización de Métricas**: Configurar Grafana para visualizar métricas de rendimiento, incluyendo uso de CPU, memoria y otros recursos del sistema.
- **Alertas**: Configurar alertas para notificar cuando se superen umbrales críticos de rendimiento o disponibilidad.

---
Estas mejoras futuras tienen el potencial de enriquecer la aplicación con capacidades adicionales de automatización, seguridad y monitorización, mejorando tanto el desarrollo como la operación continua del sistema.