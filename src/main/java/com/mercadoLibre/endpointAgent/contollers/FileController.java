package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ReciveFileDto;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;
import com.mercadoLibre.endpointAgent.services.LogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mercadoLibre.endpointAgent.services.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private LogService logService;



//Recordar enviar la ruta del archivo con la notación de doble barra // al enviar valor de filePath
// para evitar problemas con los caracteres de escape


    @Operation(summary = "Endpoint para borrar un archivo en el sistema",
            description = "Retorna los atributos del archivo borrado o un mensaje de error si el archivo no existe")
    @DeleteMapping("/delete")
    public String deleteFile(@RequestBody ReciveFileDto body) {
        try {
            // Convertir la ruta de la cadena a un objeto de tipo Path
            Path path = Paths.get(body.path());
            System.out.println("Path: " + path);

            String sha256 = fileService.calculateSha256(body.path());
            System.out.println("SHA256: " + sha256);


            // Verificar si el archivo existe
            if (Files.exists(path)) {
                // Obtener los atributos del archivo
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

                //Obtengo los atributos del archivo y los guardo en el objeto File para retornarlos en la respuesta de la petición y para registrarlos en la base de datos sqLite y en el log de la app
                File file = fileService.generateFileByBasicFileAttributes(attr, path);
                logService.deletedFile(file);

                // Borrar el archivo en el sistema
                Files.delete(path);


                return "El archivo " + body.path() + " ha sido borrado exitosamente.\n" +
                        "Fecha de creación: " + file.getCreationTime() + "\n" +
                        "Última modificación: " + file.getLastModifiedTime() + "\n" +
                        "Último acceso: " + file.getLastAccessTime() + "\n" +
                        "Tamaño: " + file.getSizeInBytes() + " bytes\n" +
                        "Tipo de archivo: " + file.getFileType() + "\n" ;


            } else {
                return "El archivo " + body.path() + " no existe.";
            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return "El sistema no puede encontrar el archivo especificado " + body.path() + " : " + e.getMessage();
        }
        catch (AccessDeniedException e){
            e.printStackTrace();
            return "No se tienen permisos para borrar el archivo " + body.path() + " : " + e.getMessage();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Error al intentar borrar el archivo " + body.path() + ": " + e.getMessage();
        }
    }

    @Operation(summary = "Endpoint para escanear un archivo en el sistema",
            description = "Retorna la respuesta de VirusTotal")

    @GetMapping("/scan")
    public String scanFile(@RequestParam() String path) {
            String apiKey = "bfed34dc176355aa97d52ee5434bc93bf96d1985c5daa52afc47cf7a8c314ab7";

            String hashSha256 = fileService.calculateSha256(path);
            String result = fileService.sendHashToVirustotal(hashSha256, apiKey);

            System.out.println("El hash SHA-256 del archivo es: " + hashSha256);
            System.out.println("Respuesta de VirusTotal: " + result);

            return result;
    }
}
