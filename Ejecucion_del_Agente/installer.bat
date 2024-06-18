@REM Explicación del Script:
@REM 1. Creación del Directorio .agent en la Carpeta del Usuario:
@REM mkdir: Comando para crear un directorio.
@REM %USERPROFILE%: Variable de entorno que representa la carpeta del perfil de usuario actual.
@REM \.agent: Nombre del directorio que se creará en la carpeta del perfil de usuario.
@REM Este comando crea un directorio llamado .agent dentro de la carpeta del perfil de usuario actual.


mkdir "%USERPROFILE%\.agent"



@REM 2. Copia de Archivos al Directorio .agent:
@REM Xcopy: Comando utilizado para copiar archivos y directorios de manera recursiva.
@REM /c: Continuar con la copia incluso si se producen errores.
@REM /i: Si el destino no existe y se está copiando más de un archivo, se trata como un directorio.
@REM /y: Suprime la solicitud de confirmación para sobrescribir archivos existentes.
@REM ". ": Punto y espacio indican que se copien todos los archivos y subdirectorios del directorio actual (.).
@REM %USERPROFILE%\.agent: Directorio de destino donde se copian los archivos y directorios del directorio actual.
@REM Este comando copia todos los archivos y subdirectorios del directorio actual al directorio recién creado .
@REM agent en la carpeta del perfil de usuario.

Xcopy /c /i /y "." "%USERPROFILE%\.agent"


@REM 3. Agregar una Entrada al Registro para Ejecutar agente.bat al Inicio del Sistema:
@REM reg add: Comando para agregar una entrada al registro de Windows.
@REM HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Run: Ruta del registro donde se almacenan las aplicaciones que se ejecutan al inicio del sistema.
@REM /v "agente": Nombre de la entrada en el registro que se va a crear.
@REM /t REG_SZ: Tipo de valor en el registro (cadena de texto).
@REM /d "%USERPROFILE%\.agent\agente.bat": Datos de la entrada del registro, que especifican la ubicación del archivo por lotes (agente.bat) que se ejecutará al inicio del sistema.
@REM Esta línea asegura que el archivo por lotes agente.bat ubicado en %USERPROFILE%\.agent se ejecute automáticamente al iniciar el sistema, agregando una entrada correspondiente en el registro de Windows.

reg add HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Run /v "agente" /t REG_SZ /d "%USERPROFILE%\.agent\agente.bat"


@REM 4. Iniciar agente.bat en una Nueva Ventana:
@REM start: Comando para iniciar un programa en una nueva ventana.
@REM "": Título de la ventana (en este caso, vacío).
@REM "%USERPROFILE%\.agent\agente.bat": Ruta completa al archivo por lotes agente.bat que se va a iniciar.
@REM Esta línea inicia el archivo por lotes agente.bat en una nueva ventana de línea de comandos.

start "" "%USERPROFILE%\.agent\agente.bat"

@REM Consideraciones Finales:
@REM Permisos: Asegúrate de tener los permisos adecuados para ejecutar estos comandos, especialmente los que modifican el registro de Windows (reg add).
@REM Ubicación de Archivos: Verifica que todos los archivos y directorios mencionados en el script existan y estén en las ubicaciones especificadas.
@REM Seguridad: Modificar el registro de Windows puede afectar el funcionamiento del sistema. Asegúrate de entender las implicaciones antes de realizar cambios en el registro.
@REM Este script automatiza la configuración para ejecutar agente.bat al inicio del sistema, lo cual puede ser útil para aplicaciones o scripts que necesitan ejecutarse automáticamente cuando se inicia Windows.