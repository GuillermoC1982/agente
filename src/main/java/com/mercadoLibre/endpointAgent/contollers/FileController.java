package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ReciveFileDto;
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

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    FileService fileService;



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

                // Obtener la fecha de creación, modificación, acceso, tamaño y tipo de archivo
                String creationTime = attr.creationTime().toString();
                String lastModifiedTime = attr.lastModifiedTime().toString();
                String lastAccessTime = attr.lastAccessTime().toString();
                long size = attr.size();
                String fileType = Files.probeContentType(path);

                // Borrar el archivo
                Files.delete(path);


                return "El archivo " + body.path() + " ha sido borrado exitosamente.\n" +
                        "Fecha de creación: " + creationTime + "\n" +
                        "Última modificación: " + lastModifiedTime + "\n" +
                        "Último acceso: " + lastAccessTime + "\n" +
                        "Tamaño: " + size + " bytes\n" +
                        "Tipo de archivo: " + fileType;


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

//    @GetMapping("/scanFile")
//    public String scanFile(){
//
//
//
//    }
}
