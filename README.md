# CRUD Tienda MVC

Aplicación de escritorio en Java que implementa un CRUD completo de productos de una tienda, siguiendo el patrón de diseño Modelo-Vista-Controlador (MVC).

## Tecnologías utilizadas

| Herramienta | Uso |
|---|---|
| Java | Lenguaje de programación |
| Maven | Gestión de dependencias |
| MySQL | Base de datos |
| JDBC | Conexión con la base de datos |
| GitHub | Control de versiones |
| Gitflow | Metodología de ramas |
| Taiga | Gestión del proyecto |
| GitBook | Documentación |
| Markdown | Formato de documentación |

## Estructura del proyecto

```text
CRUD_MVC_SSF/
├── src/
│   └── main/
│       └── java/
│           ├── Modelo/        # Clases de datos y acceso a BD
│           ├── Vista/         # Interfaz gráfica (JFrame)
│           ├── Controlador/   # Lógica de negocio
│           └── Main.java      # Punto de entrada
├── pom.xml
└── README.md
```

## Patrón MVC

* **Modelo:** Define los datos (`Producto`, `Categoria`) y gestiona las consultas a MySQL (`ConsultasTienda`).
* **Vista:** Ventana gráfica con formulario y tabla de productos (`VentanaTienda`).
* **Controlador:** Conecta la vista con el modelo y gestiona los eventos de los botones (`TiendaControlador`).

## Cómo ejecutar el proyecto

1. Clona el repositorio en tu máquina local.
2. Modifica el usuario y la contraseña de MySQL en el archivo `Modelo/BDCrear.java`.
3. Ejecuta el programa una primera vez con las líneas de `BDCrear` descomentadas para que se cree la base de datos de forma automática.
4. Vuelve a comentar esas líneas de código y ejecuta la aplicación normalmente.

## Documentación completa

[Ver documentación en GitBook]([https://gitbook.com](https://app.gitbook.com/o/e0lutnyDgxpxtg8cPpLJ/s/DfpyIFtwBoeDc00AKNLd/))

## Gitflow

```text
main
 └── develop
      └── feature/nombre-del-issue
```

## Equipo

Proyecto desarrollado como parte del módulo SSF por Álvaro Andrés Mora Acosta, Andrés Felipe Jaimes Martinez y Casandra Suárez Barrera.
