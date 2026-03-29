# 🛍️ Producto Service

## 📌 Descripción
Microservicio encargado de la gestión de productos dentro de ElectrodoStore. Permite administrar el catálogo de productos, incluyendo operaciones de creación, consulta, actualización y eliminación.

Forma parte de una arquitectura de microservicios basada en Spring Cloud.

---

## ⚙️ Tecnologías utilizadas

- Java + Spring Boot
- Spring Data JPA
- MySQL
- Spring Cloud (Eureka Client)

---

## 🧩 Responsabilidades

- Registrar nuevos productos
- Consultar catálogo de productos
- Obtener detalle de un producto
- Actualizar información de productos
- Eliminar productos

---

## 🗄️ Base de datos

Este servicio maneja su propia base de datos MySQL, siguiendo el patrón **Database per Service**, lo que garantiza independencia y desacoplamiento respecto a otros microservicios.

---

## 🔗 Endpoints principales

```http
GET    /productos
GET    /productos/{id}
POST   /productos
PUT    /productos/{id}
PATCH  /productos/{id}
DELETE /productos/{id}
```

---

## 🔄 Integración con otros servicios

Este microservicio puede ser consumido por:

- **🛒 carrito-service** → para agregar productos al carrito
- **💳 venta-service** → para validar productos durante una venta

---

## ⚠️ Manejo de errores

- Validación de datos de entrada
- Uso de códigos HTTP estándar
- GlobalExceptionHandler con @RestControllerAdvice 

---

## 🌐 Registro en Eureka

El servicio se registra automáticamente en Eureka Server, permitiendo su descubrimiento dinámico dentro del ecosistema de microservicios.

---

## ▶️ Ejecución local

> ⚠️ Requiere que **Config Server** y **Eureka Server** estén corriendo antes de iniciar este servicio.

**Con Maven**
```bash
# Corre en el puerto 8181
mvn spring-boot:run
```

**Con Docker**
```bash
docker build -t producto-service .
```

---

## 🔌 Configuración de red
| Propiedad | Valor |
|---|---|
| Puerto interno | `8181` |
| Acceso externo | ❌ Solo vía API Gateway |

---
## 🎯 Rol dentro del sistema

Este servicio es fundamental dentro del sistema, ya que centraliza la información de productos utilizada por otros microservicios, manteniendo consistencia y separación de responsabilidades.

---

## 🚀 Mejoras futuras
- Implementación de paginación y filtros en consultas
- Validaciones más robustas
- Integración con sistema de autenticación
- Cacheo de productos para mejorar rendimiento

---
