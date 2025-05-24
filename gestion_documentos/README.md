# Sistema de Gestión de Préstamos Automotrices

Este proyecto implementa un sistema completo para la gestión de préstamos automotrices, desarrollado con Spring Boot siguiendo las mejores prácticas de arquitectura de software.

## Características del Sistema

### Funcionalidades Principales
- **Gestión de Solicitudes de Crédito**: Creación, evaluación y seguimiento de solicitudes
- **Evaluación Automática**: Cálculo de scores internos y externos para evaluación crediticia
- **Cálculo Financiero**: Determinación automática de tasas, cuotas y cronogramas
- **Gestión de Estados**: Flujo completo desde solicitud hasta desembolso
- **Historial de Cambios**: Trazabilidad completa de todas las operaciones
- **Gestión Documental**: Sistema completo de documentos adjuntos por solicitud
- **Generación de Contratos**: Creación automática y gestión de contratos
- **Generación de Pagarés**: Sistema de pagarés por cuota de financiamiento
- **Auditoría Completa**: Registro de todas las operaciones del sistema

### Entidades Principales
- **ClienteProspecto**: Información personal y financiera de los solicitantes
- **SolicitudCredito**: Solicitudes de crédito con toda la información financiera
- **Vehiculo**: Catálogo de vehículos disponibles para financiamiento
- **IdentificadorVehiculo**: VIN, número de motor y placa de vehículos
- **Vendedor**: Gestión de la fuerza de ventas
- **Concesionario**: Red de concesionarios aliados
- **DocumentoAdjunto**: Documentos requeridos por solicitud
- **TipoDocumento**: Catálogo de tipos de documentos
- **Contrato**: Contratos generados para solicitudes aprobadas
- **Pagare**: Pagarés generados por cuota de financiamiento
- **HistorialEstado**: Trazabilidad de cambios de estado
- **ObservacionAnalista**: Observaciones de analistas de crédito
- **Auditoria**: Registro completo de auditoría del sistema

## Arquitectura del Sistema

### Estructura del Proyecto
```
src/main/java/com/banquito/gestion_documentos/
├── controller/           # Controladores REST
│   ├── dto/             # Data Transfer Objects
│   └── mapper/          # Mappers manuales
├── service/             # Lógica de negocio
├── repository/          # Acceso a datos con JPA
├── model/              # Entidades JPA
├── exception/          # Excepciones personalizadas
└── config/             # Configuraciones
```

### Principios Aplicados
- **Clean Architecture**: Separación clara de responsabilidades
- **Domain-Driven Design**: Modelado basado en el dominio de negocio
- **API REST**: Endpoints RESTful con buenas prácticas
- **Transaccionalidad**: Operaciones atómicas con @Transactional
- **Manejo de Errores**: Excepciones personalizadas y responses apropiados

## APIs Disponibles

### Solicitudes de Crédito
- `POST /v1/solicitudes-credito` - Crear nueva solicitud
- `GET /v1/solicitudes-credito/{id}` - Obtener solicitud por ID
- `GET /v1/solicitudes-credito/numero/{numero}` - Obtener por número
- `GET /v1/solicitudes-credito?estado={estado}` - Listar por estado (con paginación)
- `GET /v1/solicitudes-credito/concesionario/{id}` - Solicitudes por concesionario
- `PATCH /v1/solicitudes-credito/{id}/estado` - Cambiar estado

### Clientes Prospectos
- `GET /v1/clientes-prospectos/{id}` - Obtener cliente por ID
- `GET /v1/clientes-prospectos/cedula/{cedula}` - Buscar por cédula
- `GET /v1/clientes-prospectos?nombre={nombre}` - Buscar por nombre
- `GET /v1/clientes-prospectos?estado={estado}` - Filtrar por estado

### Documentos Adjuntos
- `POST /v1/documentos-adjuntos` - Adjuntar documento a solicitud
- `GET /v1/documentos-adjuntos/solicitud/{id}` - Documentos por solicitud
- `GET /v1/documentos-adjuntos/{id}` - Obtener documento por ID
- `GET /v1/documentos-adjuntos/solicitud/{id}/validacion` - Validar documentos completos
- `DELETE /v1/documentos-adjuntos/{id}` - Eliminar documento

### Contratos
- `POST /v1/contratos/generar/{idSolicitud}` - Generar contrato
- `PATCH /v1/contratos/{id}/firmar` - Firmar contrato
- `PATCH /v1/contratos/{id}/anular` - Anular contrato
- `GET /v1/contratos/solicitud/{id}` - Obtener contrato por solicitud
- `GET /v1/contratos?estado={estado}` - Listar contratos por estado

## Reglas de Negocio Implementadas

### Evaluación Crediticia
- **Score Mínimo**: 650 puntos para aprobación automática
- **Relación Cuota/Ingreso**: Máximo 40% de los ingresos netos
- **Entrada Mínima**: 10% del valor del vehículo
- **Monto de Financiamiento**: Entre $1,000 y $500,000
- **Plazo**: Entre 12 y 84 meses

### Gestión Documental
- **Documentos Obligatorios en Creación**: Cédula, Papeleta de Votación, Comprobante de Ingresos
- **Documentos Obligatorios en Aprobación**: Matrícula del Vehículo, Factura del Vehículo, Seguro
- **Validación de Duplicados**: No se permiten documentos duplicados del mismo tipo por solicitud
- **Control de Estados**: Solo se pueden eliminar documentos de solicitudes no aprobadas

### Gestión de Contratos
- **Generación Automática**: Solo para solicitudes pre-aprobadas o aprobadas
- **Validación de Documentos**: Todos los documentos requeridos deben estar completos
- **Estados de Contrato**: GENERADO → FIRMADO → ANULADO
- **Firmas Electrónicas**: Registro de fecha y usuario de firma
- **Anulación Controlada**: No se pueden anular contratos de solicitudes desembolsadas

### Cálculo de Tasas
- Tasa mínima: 8.50% (clientes con score > 750)
- Tasa máxima: 18.50% (clientes con score < 650)
- Tasas intermedias basadas en el score promedio interno/externo

### Estados de Solicitud
1. **EN_EVALUACION**: Solicitud recién creada
2. **PRE_APROBADA**: Aprobación automática por score alto
3. **EN_REVISION**: Requiere revisión manual
4. **APROBADA**: Aprobada para desembolso
5. **CONTRATADA**: Contrato firmado
6. **DESEMBOLSADA**: Dinero entregado al cliente
7. **RECHAZADA**: Solicitud rechazada

## Configuración

### Base de Datos
El sistema está configurado para PostgreSQL. Edita `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/prestamos_automotrices
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### Requisitos
- Java 21+
- PostgreSQL 12+
- Maven 3.8+

## Ejecución

1. **Clonar el repositorio**
2. **Configurar la base de datos** en `application.properties`
3. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```
4. **Acceder a la API**: `http://localhost:8080`

## Ejemplos de Uso

### Crear una Solicitud de Crédito
```bash
curl -X POST http://localhost:8080/v1/solicitudes-credito \
-H "Content-Type: application/json" \
-d '{
  "idClienteProspecto": "CLI001",
  "idVehiculo": "VEH001", 
  "idVendedor": "VEN001",
  "montoSolicitado": 25000.00,
  "plazoMeses": 48,
  "entrada": 5000.00,
  "requestId": "REQ-123456"
}'
```

### Adjuntar Documento a Solicitud
```bash
curl -X POST http://localhost:8080/v1/documentos-adjuntos \
-H "Content-Type: application/json" \
-d '{
  "idSolicitud": "SOL-123456",
  "idTipoDocumento": "CEDULA",
  "rutaArchivo": "/uploads/documentos/cedula_123456.pdf"
}'
```

### Generar Contrato
```bash
curl -X POST http://localhost:8080/v1/contratos/generar/SOL-123456 \
-H "Content-Type: application/json" \
-d '{
  "condicionEspecial": "Vehículo debe tener seguro contra todo riesgo"
}'
```

### Firmar Contrato
```bash
curl -X PATCH http://localhost:8080/v1/contratos/CON-123456/firmar \
-H "Content-Type: application/json" \
-d '{
  "usuario": "analista.credito@banquito.com"
}'
```

## Características Técnicas

### Validaciones
- Validación de datos de entrada con Bean Validation
- Validaciones de negocio personalizadas
- Idempotencia en creación de solicitudes
- Validación de documentos completos por estado

### Manejo de Errores
- Excepciones personalizadas para diferentes escenarios
- Responses HTTP apropiados
- Logging de errores para auditoría

### Paginación y Filtros
- Soporte para paginación en listados grandes
- Filtros por estado, fechas, concesionario
- Ordenamiento configurable

### Auditoría
- Historial completo de cambios de estado
- Registro de usuario y motivo en cada cambio
- Timestamps automáticos en todas las operaciones
- Tabla de auditoría para rastreabilidad completa

### Gestión Documental
- Sistema completo de tipos de documentos
- Validación automática de documentos por estado
- Control de duplicados por tipo y solicitud
- Gestión de rutas de archivos

## Extensibilidad

El sistema está diseñado para ser extensible:
- Nuevas reglas de negocio se pueden agregar fácilmente
- Integración con servicios externos (centrales de riesgo)
- Módulos adicionales (pagos, contratos, documentos)
- Notificaciones y workflows automatizados
- Sistema de archivos y almacenamiento en la nube

## Tecnologías Utilizadas

- **Spring Boot 3.5.0**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **PostgreSQL**: Base de datos
- **Lombok**: Reducción de código boilerplate
- **Jakarta Validation**: Validación de datos
- **Maven**: Gestión de dependencias

## Esquema de Base de Datos

El sistema implementa un modelo de datos completo con 13 entidades principales:

```
Concesionarios (1) ←→ (N) Vendedores
Concesionarios (1) ←→ (N) Vehiculos
Vehiculos (1) ←→ (1) IdentificadoresVehiculos
ClientesProspectos (1) ←→ (N) SolicitudesCreditos
Vehiculos (1) ←→ (N) SolicitudesCreditos  
Vendedores (1) ←→ (N) SolicitudesCreditos
SolicitudesCreditos (1) ←→ (N) HistorialEstados
SolicitudesCreditos (1) ←→ (N) ObservacionAnalistas
SolicitudesCreditos (1) ←→ (N) DocumentosAdjuntos
TiposDocumentos (1) ←→ (N) DocumentosAdjuntos
SolicitudesCreditos (1) ←→ (N) Pagares
SolicitudesCreditos (1) ←→ (N) Contratos
```

---

*Desarrollado siguiendo las mejores prácticas de arquitectura de software y principios SOLID.* 