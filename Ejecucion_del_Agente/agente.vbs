' Set WshShell = CreateObject("WScript.Shell")
' WshShell.Run "cmd /c java -jar ""C:\Users\orero\OneDrive\Escritorio\Ejecucion_del_Agente\endpointAgent-0.0.1-SNAPSHOT.jar""", 0, False 
' Set WshShell = Nothing

 Set oShell = CreateObject("WScript.Shell")
 oShell.Run "cmd /c java -jar ""%USERPROFILE%\.agent\endpointAgent-0.0.1-SNAPSHOT.jar""", 0, False

' Explicación del Script:
'  Archivo VBS: El archivo VBS crea un objeto WScript.Shell que ejecuta el 
'  comando java -jar en una nueva ventana de comando (cmd /c). 
 
'  El parámetro 0 indica que la ventana de comandos se ejecutará oculta (en segundo plano) y False especifica que no se espera
'   a que el comando se complete antes de continuar.

' Archivo Batch: El archivo por lotes ahora ejecuta el archivo VBS en lugar de ejecutar directamente el comando java -jar.
'  Esto permite que el archivo JAR se ejecute en segundo plano según lo especificado por el archivo VBS.

' Al seguir estos pasos, deberías poder ejecutar tu archivo JAR en segundo plano desde el archivo por lotes 
' en Windows usando un archivo VBS auxiliar. Esto te permite tener control sobre cómo se maneja la ejecución 
' del archivo JAR mientras se ejecuta en segundo plano.