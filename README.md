## Sistemas Orientados a Servicios

### Introduction
Contenedor de desarrollo basico para la Practica de Sistemas Orientados a Servicios
Este entorno de desarrollo contiene Java 21, Maven, Git, Docker y algunas dependencias en el pom.xml
El entorno tambien contiene algunas extensiones para Java, Redhat Community Server Connector para Debugging entre otros
___
### Getting Started
Prequisito: WSL 2 de Windows o Docker

1. Descarga la extension `Dev Containers` para VSCode
2. Descarga o clona el repositorio mediante `git clone https://github.com/Bodiw/jakarta-example`
3. Abre la carpeta en VSCode, y sigue las sugerencias de VSCode `Reopen in Container`. Esto descargará Docker si no lo has hecho aún

Si vas a usar este contenedor de desarrollo, asegurate de cambiar los `com.example` en `pom.xml` y los directorios y clases en `src/main/`
___
## Compile
Se puede compilar el proyecto y generar un ROOT.war, que creará un `target/ROOT.war` mediante el siguiente comando:
```bash
mvn clean package
```
El .war generado es desplegable en aplicaciones Tomcat, por defecto en la carpeta `/usr/local/tomcat/webapps`
___
## Build
Por defecto, el proyecto dispone de un `Hello World!!!` disponible en el path `/api/hello`.
Usando los comandos de despliegue abajo, suele estar disponible en el puerto 80,forwardeado por vscode, a menos que el puerto 80 ya esté ocupado y VSCode buscará otro puerto
URL:`http://localhost/api/hello` o `http://localhost:80/api/hello`

### 1. Crear la imagen

```bash
docker build -t sos .
```

### 2. Ejecutar el contenedor

```bash
docker run -p 80:8080 sos
```
En su defecto, esto expone la aplicacion en el puerto 80, y `Hello World!!!` es accesible en `http://localhost/api/hello`
___
## Debugging
El contenedor incluye la extension de Redhat Community Server Connector, que permite depurar la API mediante el debugger de VSCode

1. Crear ROOT.war mediante `mvn package`
2. En el panel lateral de VSCode, seleccionar la pestaña Explorer:Servers
3. En Community server Connector, Click derecho y Create new Server. Selecciona las opciones: `Yes`, `Tomcat 11.0.0-M6`, `Continue`, `Yes`
4. En la pestaña de `apache-tomcat-11.0.0-M6`, Click Derecho y `Add Deployment`, `File`. Selecciona el `target/ROOT.war` y por ultimo `No`
5. De nuevo en `apache-tomcat-11.0.0-M6`, click derecho y `Publish Server (Full)`

Para depurar, los pasos son:
1. `Click Derecho` en `apache-tomcat-11.0.0-M6` y `Debug Server`. Si sale la pagina de inicio, y no un `404 Not Found`, para Tomcat con `Stop Server` y en la misma pestaña selecciona `ROOT.war`,`Click Derecho`, `Remove Deployment`, y repite los pasos anteriores `4` y `5`. Por ultimo, vuelve a iniciar el servidor en `Debug Server`

Si el Servidor de Debug se esta ejecutando, los cambios en el codigo fuente se propagan de manera automatica despues de `mvn package` y unos segundos