-- Ejecutar SOLO si se quiere poblar con datos de prueba manualmente
-- El DataSeeder ya crea estos usuarios automáticamente al arrancar la app

USE marketplace_db;

-- Las contraseñas ya están encodeadas con BCrypt:
-- admin123  → $2a$10$... (generado por BCrypt)
-- teacher123
-- student123

-- Si el ddl-auto=update ya creó las tablas, estos inserts son opcionales
-- El DataSeeder en Java hace lo mismo al arrancar Spring Boot.
