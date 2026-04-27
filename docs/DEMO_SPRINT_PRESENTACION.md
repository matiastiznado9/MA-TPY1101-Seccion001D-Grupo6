# DEMO FUNCIONAL SPRINT 1 — SEGURIDAD Y AUTENTICACIÓN

## Proyecto

**ConectaTarot**

## Integrantes

* Pablo Rebolledo (Backend)
* Oscar Clavijo (Frontend)
* Matías Tiznado (Scrum Master)
* Ricardo Gaete (Product Owner)

---

# Objetivo del Sprint

Implementar un MVP funcional de seguridad para la plataforma:

Historias completadas:

## HU-USER-01

✅ Registro de usuario

## HU-USER-02

✅ Inicio de sesión

## HU-SEC-01

✅ Autenticación con JWT

## HU-SEC-02

✅ Manejo de roles

Extra implementado:

✅ Logout con invalidación de token (blacklist)

---

# Arquitectura implementada

## Backend

* Spring Boot
* Spring Security
* JWT
* BCrypt
* JPA / Hibernate
* MySQL

## Seguridad

Flujo implementado:

```text
Usuario Login
↓
Generación JWT
↓
Filtro JwtAuthFilter valida token
↓
Spring Security autoriza según rol
↓
Acceso / rechazo (403 / 401)
```

---

# Evidencias funcionales (Demo Narrada)

---

# 1 Registro de usuario

Endpoint:

```http
POST /api/usuarios
```

Request:

```json
{
 "nombre":"Cliente Demo",
 "email":"cliente.demo@test.com",
 "password":"123456"
}
```

Resultado esperado:

```http
201 Created
```

Validaciones implementadas:

* Email único
* Password cifrada con BCrypt
* Rol CLIENTE asignado por defecto

Resultado:

✅ Historia cumplida

---

# 2 Inicio de sesión

Endpoint:

```http
POST /api/auth/login
```

Request:

```json
{
 "email":"cliente.demo@test.com",
 "password":"123456"
}
```

Respuesta:

```json
{
 token: "JWT...",
 rol: "CLIENTE"
}
```

Incluye:

* Validación de credenciales
* Generación JWT
* Expiración token 24 horas
* Rol embebido en token

Resultado:

✅ Historia cumplida

---

# 3 Ruta protegida con JWT

Endpoint:

```http
GET /api/auth/perfil
```

Con Bearer Token:

Respuesta:

```http
200 OK
Acceso permitido con JWT
```

Demuestra:

* autenticación funcional
* filtro JWT operativo
* seguridad aplicada

Resultado:

✅ Historia cumplida

---

# 4 Control de roles

## Usuario CLIENTE intenta entrar a endpoint ADMIN

Endpoint:

```http
GET /api/auth/admin
```

Resultado:

```http
403 Forbidden
```

Interpretación:

Usuario autenticado

Pero:

No autorizado por rol.

Control realizado mediante:

```java
@PreAuthorize("hasRole('ADMIN')")
```

Resultado:

✅ Control de roles funcionando

---

# 5 Acceso ADMIN válido

Login admin:

```json
{
 "email":"admin@test.com",
 "password":"123456"
}
```

Endpoint:

```http
GET /api/auth/admin
```

Resultado:

```http
200 OK
Acceso permitido solo para ADMIN
```

Resultado:

✅ Autorización por roles funcionando

---

# 6 Logout Seguro (Extra)

Se implementó mejora adicional no mínima requerida.

Endpoint:

```http
POST /api/auth/logout
```

Proceso:

* token se agrega a blacklist
* token queda invalidado

Respuesta:

```http
Sesión cerrada correctamente
```

---

# 7 Reutilización token invalidado

Reintento usando mismo JWT:

```http
GET /api/auth/admin
```

Resultado:

```http
401 Unauthorized
Token invalidado por logout
```

Demuestra:

* logout real
* invalidación efectiva
* protección ante reutilización de token

Resultado:

✅ Mejora adicional completada

---

# Seguridad implementada

## BCrypt

Contraseñas no se almacenan en texto plano.

---

## JWT

Incluye:

* subject (email)
* rol
* expiración

---

## JwtAuthFilter

Valida:

* token presente
* token válido
* token no esté en blacklist
* construye contexto de seguridad

---

## Roles soportados

```text
ADMIN
CLIENTE
TAROTISTA
```

---

# Base de Datos

Modelo implementado:

```text
rol
└── usuario (FK rol_idRol)
```

Roles precargados:

* ADMIN
* CLIENTE
* TAROTISTA

---

# Endpoints desarrollados

| Endpoint         | Método | Estado |
| ---------------- | ------ | ------ |
| /api/usuarios    | POST   | ✅      |
| /api/auth/login  | POST   | ✅      |
| /api/auth/perfil | GET    | ✅      |
| /api/auth/admin  | GET    | ✅      |
| /api/auth/logout | POST   | ✅      |

---

# Resumen técnico entregado

Implementado:

* Registro usuario
* Inicio sesión
* JWT
* Protección endpoints
* Manejo roles
* Logout seguro
* Blacklist tokens
* README de entorno para replicabilidad

---

# Criterios del Sprint cumplidos

## Funcionalidad

✅ Cumplido

## Seguridad

✅ Cumplido

## Control acceso por rol

✅ Cumplido

## Persistencia usuarios

✅ Cumplido

## Validación endpoints

✅ Cumplido

## Mejora adicional

✅ Logout con blacklist

---

# Estado Sprint

## Story points completados

100%

Sprint completado.

---

# Nota para revisión docente

Debido a que el entorno local (Java, MySQL y configuración del backend) no está disponible en sala, esta documentación deja evidencia del flujo funcional implementado y del alcance técnico efectivamente desarrollado en el sprint.

Además el repositorio incluye README para replicar el entorno completo.

---

## Repositorio

GitHub:

```text
MA-TPY1101-Seccion001D-Grupo6
```

---

Fin demo.
