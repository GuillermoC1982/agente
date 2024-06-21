# Challenge Agente Mercado Libre

## Requisitos previos

Lo primero que debemos hacer para probar esta aplicación es descargar el JDK 17, que es la versión con la cual estuve desarrollando la API. 

Dejo el link por [aquí](https://www.oracle.com/java/technologies/downloads/#jdk17-windows).

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

En este momento, la aplicación debería estar corriendo en segundo plano.
Eso lo podemos confirmar accediendo a swagger con el siguiente link y viendo allí toda la documentación de los end-points realizados aqui: 
[http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#/)

Y se deberia ver algo asi:

![image](https://github.com/GuillermoC1982/agente/assets/73852715/4e9fa4a1-cb9a-4bc1-842a-f9402531d599)

En este punto, se puede probar la aplicación registrando un nuevo usuario, ya que el endpoint está abierto por fines prácticos. 
Si al intentar registrar un usuario se produce un error de SQL, prueba borrando la base de datos y volviendo a ejecutar el instalador. 
Si esto no funciona, copia la base de datos que está en el proyecto y reemplázala por la que se genera.

![image](https://github.com/GuillermoC1982/agente/assets/73852715/75c61b06-e5ef-4a02-873f-cf9b4bfbafad)
![image](https://github.com/GuillermoC1982/agente/assets/73852715/7a523b18-bcec-4f8a-b340-7ac9f7c61e53)

## Diagrama de entidad de relación

Incluyo el diagrama de entidad de relación desarrollado para la aplicación, el cual ha evolucionado durante el proceso.

![image](https://github.com/GuillermoC1982/agente/assets/73852715/74779afc-9236-42f3-8285-8142c4bd163c)


## Consideraciones adicionales

- A fines prácticos, dejé codificada mi API key para que puedan realizar pruebas en VirusTotal. Entiendo que debería estar alojada como una variable de entorno por razones de seguridad, ya que es un dato sensible.
- He dejado también un registro libre y abierto de usuarios, ya que entiendo que a mi modo de ver es una aplicación de escritorio para que los usuarios de una pc en particular puedan registrar los movimientos al usarla, ver registros, escanear y borrar archivos, etc. Sin embargo, si la aplicación fuera a escala más grande, debería validar el registro de usuarios de manera más rigurosa, incluyendo reglas de verificación para correo electrónico usando expresiones regulares.

-  Además, podría considerarse que solo usuarios autenticados puedan habilitar nuevos registros de usuarios.
 Pero bueno, en este caso decidí dejarlo más simple para que sea más fácil probar su funcionalidad .


- La aplicación cuenta con los siguientes endpoints para:
  
  - #Login
  - 
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/051026b3-9ed5-4ec6-8ddd-7ed731b3fa4f)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/b26b991e-2e8a-4406-b60e-308da07d1509)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/31fe2274-6068-4b38-a0a9-76964b19bd97)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/b439d46e-c7ae-443c-b05d-42b0bc3033cc)

  - #Registrar un cliente.
 
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/ce1c92cf-6c8c-49c2-aa59-f3a18e60dfa1)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/858871b1-328a-4063-a6c6-b29d0dc339fd)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/570ea27c-a607-48ca-a28f-ccc3ec023607)

  - #Obtener el listado de todos los clientes.
 
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/056b3ad3-472f-4c97-aac4-941859a6d89d)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/50d2152e-1895-4bec-ae51-e695bd8c2d3b)

  - #Obtener el listado de todos los logs.
 
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/a8b345c2-b1e7-4395-a6a1-4a8cdc70b7ac)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/78485fdd-2c11-4a73-854b-c235d49f1eca)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/5806fea2-d05a-4c26-8661-2933605c9743)


  - #Obtener los logs del cliente logueado.
 
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/38c00feb-b8f0-48d6-8412-5fbc8a0b1dbf)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/0ce5e6dc-480e-44f8-a00e-15dc201ac347)
    ![image](https://github.com/GuillermoC1982/agente/assets/73852715/9e35b38d-1715-4b9e-b9da-51b9e212c612)

  - #Escanear un archivo.
    
   ![image](https://github.com/GuillermoC1982/agente/assets/73852715/14dcbc65-8640-4e83-9172-56f149971bfb)
   ![image](https://github.com/GuillermoC1982/agente/assets/73852715/9fff39a2-d457-413c-81bd-bfb00c61f0b5)
   ![image](https://github.com/GuillermoC1982/agente/assets/73852715/a9b49c2f-84ec-4f97-a1ce-c1582f675976)
    
  - #Borrar un archivo.
 
  ![image](https://github.com/GuillermoC1982/agente/assets/73852715/cfe1200a-1dbf-4028-8baa-c687375df02c)
  ![image](https://github.com/GuillermoC1982/agente/assets/73852715/f76e91ba-6898-4bf5-9d8b-c7bc3767e00a)
  ![image](https://github.com/GuillermoC1982/agente/assets/73852715/ac372a19-f534-4e59-ac20-8c4191b3e70c)



- Solo se han realizado tests de aceptación debido a limitaciones de tiempo y recursos.

- Y eso seria todo lo que pude llegar hacer para entregar el proyecto en tiempo y forma, seguramente falten algunas cosas pero tiene mucho amor y dedicacion.

# ¡Muchas gracias por la oportunidad! Aprendí mucho durante este challenge y tambien disfruté mucho el proceso.


