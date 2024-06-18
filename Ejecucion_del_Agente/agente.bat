@echo off
start "" "%USERPROFILE%\.agent\agente.vbs" 
pause

@REM Explicación del Script:
@REM @echo off: Esta línea desactiva la impresión de los comandos en la consola mientras se ejecuta el script.
@REM  Es opcional, pero comúnmente se usa para hacer que la salida sea más limpia.

@REM java -jar "C:\Users\orero\OneDrive\Escritorio\Ejecucion_del_Agente\endpointAgent-0.0.1-SNAPSHOT.jar": 
@REM Este comando ejecuta el archivo JAR especificado. Asegúrate de que la ruta al archivo JAR esté escrita correctamente y 
@REM sea la misma que la ubicación real del archivo en tu sistema.

@REM Si el archivo JAR está en una carpeta diferente o tiene un nombre diferente, asegúrate de ajustar la ruta y 
@REM el nombre del archivo JAR en consecuencia.

@REM pause: Esta línea detiene la ejecución del script después de que se haya ejecutado el archivo JAR. 
@REM Esto es útil para mantener la ventana de la consola abierta y permitirte ver cualquier mensaje de salida que pueda aparecer.