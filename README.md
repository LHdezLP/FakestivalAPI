# Documentación de la API de Gestión de Festivales

## 1. Modelo de Datos

### Modelo Entidad-Relación

#### Modelo UML

- **Usuario**
  - `+ id_usuario: bigint (PK)`
  - `- nombre: String`
  - `- email: String`
  - `- telefono: String`
  - `- contraseña: String`
  - `- apellido: String`
  - `token: String`
  - `+ Getter()`
  - `+ Setter()`

- **WISHLIST**
  - `+ ID_WISLIST: bigint (PK)`
  - `- nombre: String`
  - `- id_usuario: String`
  - `- Banda: String`
  - `+ Getter()`
  - `+ Setter()`

---

#### Modelo Relacional

- **Usuario**(`id_usuario`, nombre, apellido, contraseña, telefono, email, token)
- **Wishlist**(`id_wishlist`, nombre, `id_usuario`, {banda})

---

### Descripción de las Tablas

**Tabla: usuario**

- `id_usuario (BIGINT, PK)` - Identificador único del usuario.
- `nombre (VARCHAR(255))` - Nombre del usuario.
- `apellido (VARCHAR(255))` - Apellido del usuario.
- `contrasena (VARCHAR(255), Único)` - Contraseña del usuario.
- `telefono (VARCHAR(20))` - Teléfono del usuario.
- `email (VARCHAR(255), Único)` - Email único del usuario.
- `token (VARCHAR(255))` - Token de autenticación.

**Tabla: wishlist**

- `id_wishlist (BIGINT, PK)` - Identificador único de la wishlist.
- `nombre (VARCHAR(255))` - Nombre de la wishlist.
- `id_usuario (BIGINT, PK)` - Clave foránea que referencia a la tabla usuario.
- `banda (VARCHAR(255))` - Nombre de la banda en la wishlist.

---

### Relaciones

1. La tabla `usuario` tiene una relación uno a muchos con la tabla `wishlist` (un usuario puede tener varias wishlists).
2. La tabla `wishlist` tiene una relación uno a muchos con la tabla `wishlist_bandas` (una wishlist puede contener varias bandas).
3. La eliminación de un usuario puede implicar la eliminación en cascada de sus wishlists.
4. La eliminación de una wishlist puede implicar la eliminación en cascada de sus bandas asociadas.

---

## 2. Descripción del Sistema

El sistema de gestión de festivales es una API RESTful diseñada para administrar usuarios y sus listas de deseos (wishlists) relacionadas con bandas musicales. La API permite:

- Registrar y autenticar usuarios.
- Gestionar perfiles de usuarios (crear, leer, actualizar y eliminar).
- Crear, modificar y eliminar wishlists asociadas a un usuario.
- Autenticación basada en tokens JWT (JSON Web Tokens) para asegurar los endpoints.

El sistema está desarrollado en Java con Spring Boot y utiliza una base de datos relacional para almacenar la información. La autenticación se realiza mediante JWT, y las contraseñas se almacenan de forma segura utilizando Bcrypt.

---

## 3. Funciones del Sistema

### Funcionalidades de Usuario

- **Registro de usuarios**: Los usuarios pueden registrarse proporcionando su nombre, apellido, email, contraseña y teléfono.
- **Autenticación**: Los usuarios pueden iniciar sesión utilizando su email y contraseña. Si las credenciales son válidas, se genera un token JWT.
- **Gestión de perfiles**: Los usuarios pueden actualizar su información personal (nombre, apellido, email y teléfono) y eliminar su cuenta.
- **Recuperación de información**: Los usuarios pueden obtener su información personal y su token JWT.

### Funcionalidades de Wishlist

- **Creación de wishlists**: Los usuarios pueden crear listas de deseos con un nombre y una lista de bandas.
- **Modificación de wishlists**: Los usuarios pueden actualizar el nombre y las bandas de una wishlist existente.
- **Eliminación de wishlists**: Los usuarios pueden eliminar una wishlist.
- **Consulta de wishlists**: Los usuarios pueden obtener todas sus wishlists o una en particular.

---

## 4. Arquitectura

El sistema sigue una arquitectura en capas:

- **Capa de Presentación**: Controladores REST que manejan las solicitudes HTTP.
- **Capa de Servicio**: Lógica de negocio para gestionar usuarios y wishlists.
- **Capa de Persistencia**: Repositorios que interactúan con la base de datos.
- **Capa de Seguridad**: Filtros y configuraciones para autenticación y autorización.

---

## Tecnologías Utilizadas

- **Spring Boot**: Framework para desarrollar aplicaciones Java.
- **Spring Data JPA**: Para la persistencia de datos.
- **Spring Security**: Para la autenticación y autorización.
- **JWT**: Para la generación y validación de tokens.
- **BCrypt**: Para el hashing de contraseñas.

---

## 5. Documentación de la API: ORM, Anotaciones y Uso de Spring

En este proyecto, se utiliza JPA (Java Persistence API) como estándar de ORM, y Hibernate como implementación de JPA. Las anotaciones de JPA se utilizan para definir cómo se mapean las entidades y sus relaciones con la base de datos.

- `@ElementCollection`
- `@CollectionTable`
- `@ManyToOne`
- `@JoinColumn`
- `@Service`
- `@Autowired`
- `@RestController`
- `@RequestMapping`

---

## 6. Prerrequisitos del Sistema

1. Tener Postman instalado.
2. Java 21 o superior.
3. Un IDE como IntelliJ en el que inicializar la API.
4. Descargar el zip del [Repositorio GitHub](https://github.com/LHdezLP/FakestivalAPI.git).

---

## 7. Ejemplos de Uso

### Registro de Usuario
- **POST** `http://localhost:8080/usuario`

### Autenticación
- **POST** `http://localhost:8080/user`

### Crear Wishlist
- **POST** `http://localhost:8080/wishlist`

### Modificar Wishlist
- **PUT** `http://localhost:8080/wishlist/1`

### Obtener Wishlists
- **GET** `http://localhost:8080/wishlist/usuario/1`

### Borrar Wishlist
- **DELETE** `http://localhost:8080/wishlist/{id}`

---

## 8. Consideraciones Finales

La API está diseñada para ser escalable y segura, utilizando JWT y BCrypt. Se recomienda usar Postman para probar los endpoints.

