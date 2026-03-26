# Endpoints implementados

## 1) Crear usuario (admin)
`POST /api/admin/users`

```json
{
  "name": "Pedro Profesor",
  "email": "pedro@marketplace.com",
  "role": "TEACHER"
}
```

También se puede crear un alumno usando:

```json
{
  "name": "Lucia Alumna",
  "email": "lucia@marketplace.com",
  "role": "STUDENT"
}
```

---

## 2) Listar clases
`GET /api/classes`

---

## 3) Crear clase
`POST /api/classes`

```json
{
  "title": "Programación Java",
  "description": "Clase inicial de objetos",
  "durationInMinutes": 90,
  "teacherId": 2
}
```

---

## 4) Inscribirse a una clase
`POST /api/enrollments`

```json
{
  "studentId": 3,
  "courseId": 1
}
```

---

## 5) Ver clases inscriptas de un alumno
`GET /api/students/3/enrollments`

---

## Datos precargados en memoria
- Admin: `id = 1`
- Profesor: `id = 2`
- Alumna: `id = 3`
- Clase: `id = 1`
