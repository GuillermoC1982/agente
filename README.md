# Challenge Agente Mercado Libre

## Requisitos previos

Lo primero que debemos hacer para probar esta aplicación es descargar el JDK 17, que es la versión con la cual estuve desarrollando la API. Dejo el link por [aquí](https://www.oracle.com/java/technologies/downloads/#jdk17-windows).

Instalar :)

Verifica la instalación ejecutando `java --version` en la terminal o cmd.

### Configuración de variables de entorno (opcional)

Una vez descargado la JDK, puede que deban configurar las variables de entorno **(probar sin configurar las variables y saltar a ejecucion del agente primero)**.

1. Abrir las propiedades del sistema e ir a "Configuración avanzada del sistema".
2. En la pestaña "Opciones avanzadas", haz clic en "Variables de entorno".
3. Crear una nueva variable de entorno llamada `JAVA_HOME` y establece su valor en la carpeta de instalación de JDK 17.
4. En la variable de entorno `Path`, añade `%JAVA_HOME%\bin`.

![image](https://github.com/GuillermoC1982/agente/assets/73852715/c0c34719-1323-4b63-bae1-60f9533f7ad4)
![image](https://github.com/GuillermoC1982/agente/assets/73852715/626c9886-dfb8-4dc9-8794-1bbdf581f2fa)
![image](https://github.com/GuillermoC1982/agente/assets/73852715/73eedf75-d548-439a-acdf-14f26368ca7d)
![image](https://github.com/GuillermoC1982/agente/assets/73852715/5964490d-b1ac-42d7-9cf9-fa7893aac5ee)

## Ejecución del agente

1. Ingresar en la carpeta, valga la redundancia, "Ejecución_del_agente" en la raíz del proyecto.
2. Ejecuta `installer.bat` como administrador.
3. Si aparece un mensaje, haz clic en "Run Away".
4. La aplicación debería ejecutarse en segundo plano como un daemon en Windows.

Para confirmar que la aplicación está corriendo, accede a Swagger a través de [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#/) para ver la documentación de los endpoints.

Y se deberia ver algo asi:

![image](https://github.com/GuillermoC1982/agente/assets/73852715/4e9fa4a1-cb9a-4bc1-842a-f9402531d599)

En este punto, se puede probar la aplicación registrando un nuevo usuario, ya que el endpoint está abierto por fines prácticos. 
Si al intentar registrar un usuario se produce un error de SQL, prueba borrando la base de datos y volviendo a ejecutar el instalador. 
Si esto no funciona, copia la base de datos que está en el proyecto y reemplázala por la que se genera.

## Diagrama de entidad de relación

Incluyo el diagrama de entidad de relación desarrollado para la aplicación, el cual ha evolucionado durante el proceso.

![image](https://github.com/GuillermoC1982/agente/assets/73852715/74779afc-9236-42f3-8285-8142c4bd163c)


## Consideraciones adicionales

- Mi API key para VirusTotal está codificada en la aplicación para facilitar las pruebas. Se recomienda alojarla como una variable de entorno por razones de seguridad.
- El registro de logs está abierto para facilitar la prueba de funcionalidad. Para una aplicación a mayor escala, se debería implementar autenticación rigurosa y validación de usuarios.
- La aplicación cuenta con los siguientes endpoints:

  - Registrar un cliente.
  - Obtener el listado de todos los clientes.
  - Obtener el listado de todos los logs.
  - Obtener los logs del cliente logueado.
  - Escanear un archivo.
  - Borrar un archivo.

- Solo se han realizado tests de aceptación debido a limitaciones de tiempo y recursos.

## Agradecimientos

¡Gracias por la oportunidad! Aprendí mucho durante este challenge y disfruté el proceso.
