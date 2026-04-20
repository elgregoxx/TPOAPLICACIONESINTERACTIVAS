# Marketplace de Cursos — TPO Aplicaciones Interactivas

API REST de marketplace de cursos online con autenticación JWT y autorización por roles.

## Integrantes

| Nombre | Legajo |
|--------|--------|
| (Nombre 1) | (Legajo 1) |
| (Nombre 2) | (Legajo 2) |
| (Nombre 3) | (Legajo 3) |

## Tecnologías

- Java 17
- Spring Boot 3.2.5
- Spring Security + JWT (jjwt 0.12.5)
- Spring Data JPA + Hibernate
- MySQL 8
- BCrypt (PasswordEncoder)
- Lombok

## Requisitos previos

- JDK 17+
- Maven 3.8+
- MySQL 8 corriendo en localhost:3306

## Pasos para ejecutar localmente

**1. Clonar el repositorio**
```bash
git clone https://github.com/TU_USUARIO/TU_REPO.git
cd TU_REPO
```

**2. Crear la base de datos**
```sql
CREATE DATABASE marketplace_db;
```

**3. Configurar credenciales**
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Editá `application.properties` y completá tu usuario y contraseña de MySQL.

**4. Compilar y ejecutar**
```bash
./mvnw spring-boot:run
```

La aplicación arranca en `http://localhost:8080`.  
Al iniciar, el `DataSeeder` crea automáticamente 3 usuarios de prueba en la BD.

## Usuarios de prueba

| Email | Password | Rol |
|-------|----------|-----|
| admin@marketplace.com | admin123 | ROLE_ADMIN |
| juan@marketplace.com | teacher123 | ROLE_TEACHER |
| ana@marketplace.com | student123 | ROLE_STUDENT |

## Endpoints

### Autenticación (público)
| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /api/auth/login | Login → devuelve JWT |

### Usuarios (solo ROLE_ADMIN)
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/admin/users | Listar todos los usuarios |
| GET | /api/admin/users/{id} | Obtener usuario por ID |
| POST | /api/admin/users | Crear usuario |
| DELETE | /api/admin/users/{id} | Eliminar usuario |

### Cursos (autenticado)
| Método | URL | Rol requerido | Descripción |
|--------|-----|---------------|-------------|
| GET | /api/classes | Cualquier rol | Listar cursos |
| GET | /api/classes/{id} | Cualquier rol | Obtener curso |
| POST | /api/classes | ADMIN / TEACHER | Crear curso |
| PUT | /api/classes/{id} | ADMIN / TEACHER | Actualizar curso |
| DELETE | /api/classes/{id} | ADMIN | Eliminar curso |

### Inscripciones
| Método | URL | Rol requerido | Descripción |
|--------|-----|---------------|-------------|
| GET | /api/enrollments | STUDENT / ADMIN | Listar inscripciones |
| POST | /api/enrollments | STUDENT / ADMIN | Crear inscripción |
| GET | /api/students/{id}/enrollments | Autenticado | Inscripciones de un alumno |
| DELETE | /api/enrollments/{id} | STUDENT / ADMIN | Eliminar inscripción |

## Uso del token JWT

Luego del login, incluir el token en cada request:
```
Authorization: Bearer <token>
```

## Arquitectura

```
Controller → Service → Repository → MySQL
               ↑
         Security Filter Chain
         JwtAuthFilter → JwtService → SecurityContextHolder
```

## Colección Postman

Importar el archivo `marketplace-postman-collection.json` incluido en el proyecto.
