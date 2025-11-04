# Sistema de Gestion Integral para la Veterinaria "Happy Feet"

## Descripcion del Contexto

La clinica veterinaria "Happy Feet" enfrenta desafios operativos debido a su exito y crecimiento. Su sistema actual, basado en papel y hojas de calculo, resulta en historiales clinicos incompletos, control de inventario deficiente, agendamiento caotico y facturacion lenta y propensa a errores. Este proyecto implementa un Sistema de Gestion Integral para centralizar y automatizar todas las operaciones de la clinica, desde la ficha del paciente hasta la facturacion y los reportes gerenciales, asegurando la calidad del servicio y profesionalizando la administracion.

## Tecnologias Utilizadas

*   **Lenguaje:** Java (JDK 17)
*   **Base de Datos:** MySQL
*   **Conexion a BD:** JDBC (Java Database Connectivity)
*   **Gestion de Dependencias y Construccion:** Apache Maven
*   **Arquitectura:** Modelo-Vista-Controlador (MVC)

## Funcionalidades Implementadas

El sistema esta organizado en cinco modulos principales:

#### 1. Modulo de Gestion de Pacientes
*   CRUD completo para Dueños (crear, buscar por documento, actualizar, eliminar).
*   CRUD completo para Mascotas (crear, buscar, actualizar, eliminar).
*   Transferencia de propiedad de una mascota de un dueño a otro.

#### 2. Modulo de Servicios Medicos y Citas
*   Agendamiento, consulta y actualizacion de estado de citas medicas.
*   Registro detallado de consultas medicas y procedimientos especiales.
*   Creacion de prescripciones medicas asociadas a una consulta.
*   **Regla de Negocio Clave:** Al crear una prescripcion, el stock del medicamento recetado se descuenta automaticamente del inventario.

#### 3. Modulo de Inventario y Farmacia
*   CRUD completo para el catalogo de Productos (medicamentos, vacunas, insumos).
*   CRUD completo para la gestion de Proveedores.
*   Ajuste manual de stock para reflejar el inventario fisico.
*   **Alertas Inteligentes:** Generacion de reportes en consola para productos con stock bajo y productos proximos a vencer.

#### 4. Modulo de Facturacion y Reportes
*   Generacion de facturas en formato de texto plano (`.txt`) para dos escenarios:
    1.  **Venta Directa:** Facturacion de productos o servicios sueltos.
    2.  **Facturacion Post-Atencion:** Busqueda de consultas medicas de un cliente para facturar el servicio y los productos prescritos.
*   Aplicacion de descuentos en la factura mediante el canje de puntos del Club de Mascotas.
*   **Reportes Gerenciales** generados en consola:
    *   Servicios mas solicitados.
    *   Desempeño del equipo veterinario (basado en numero de consultas).
    *   Estado del inventario (stock bajo y a vencer).
    *   Analisis de facturacion por periodo de fechas.
*   Gestion (CRUD) del catalogo de Servicios que ofrece la clinica.

#### 5. Modulo de Actividades Especiales
*   **Adopciones:** Puesta de mascotas en adopcion y registro de la adopcion final, generando un contrato simple en formato `.txt`.
*   **Jornadas de Vacunacion:** Creacion de campañas y una interfaz de registro rapido para vacunar masivamente, descontando el stock de cada vacuna aplicada.
*   **Club de Mascotas Frecuentes:**
    *   Inscripcion de dueños al club.
    *   Acumulacion automatica de puntos basada en el total de las facturas.
    *   Creacion y gestion de beneficios (premios).
    *   Canje de puntos por beneficios, que pueden ser aplicados como descuentos en futuras facturas.

## Modelo de la Base de Datos

El sistema utiliza una base de datos relacional en MySQL para garantizar la integridad y consistencia de la informacion. Las tablas principales incluyen `duenos`, `mascotas`, `citas`, `consultas_medicas`, `inventario` y `facturas`, interconectadas a traves de llaves foraneas.


## Instrucciones de Instalacion y Ejecucion

Siga estos pasos para configurar y ejecutar el proyecto en su entorno local.

#### Requisitos Previos

*   **JDK 17** o superior.
*   **Apache Maven** 3.6 o superior.
*   **Servidor MySQL** 8.0 o superior.

#### 1. Clonacion del Repositorio

Clone este repositorio en su maquina local usando el siguiente comando:
```bash
git clone [https://github.com/JhonierQuintero/HappyFeet_Veterinaria_QuinteroJhonier.git]
```

#### 2. Configuracion de la Base de Datos

El proyecto se conecta a la base de datos a traves de la clase `util/ConexionDB.java`. Abra este archivo y modifique las credenciales para que coincidan con su configuracion local de MySQL.

```java
// Dentro de util/ConexionDB.java
private static final String URL = "jdbc:mysql://localhost:3306/happy_feet_veterinaria";
private static final String USUARIO = "root"; // <-- Cambie por su usuario
private static final String CONTRASENA = "password"; // <-- Cambie por su contraseña
```

#### 3. Ejecucion de Scripts SQL

1.  Abra su cliente de MySQL preferido (MySQL Workbench, DBeaver, etc.).
2.  Cree la base de datos ejecutando el script ubicado en `/database/schema.sql`. Esto creara todas las tablas y relaciones.
3.  Poble la base de datos con datos de prueba ejecutando el script ubicado en `/database/data.sql`. Esto insertara los datos iniciales necesarios para que el sistema funcione (especies, tipos de producto, servicios predeterminados, etc.).


## Guia de Uso

Una vez ejecutada la aplicacion, se presentara un menu principal en la consola. Simplemente ingrese el numero correspondiente al modulo que desea utilizar y siga las instrucciones en pantalla. Para salir de un sub-menu, ingrese la opcion `0`.

## Autor(es)

* Jhonier Quintero
