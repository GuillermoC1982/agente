package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ReciveFileDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

@RestController
@RequestMapping("/api/file")
public class FileController {


//Recordar enviar la ruta del archivo con la notación de doble barra // al enviar valor de filePath
// para evitar problemas con los caracteres de escape


    @Operation(summary = "Endpoint para borrar un archivo en el sistema",
            description = "Retorna los atributos del archivo borrado o un mensaje de error si el archivo no existe")
    @DeleteMapping("/delete")
    public String deleteFile(@RequestBody ReciveFileDto body) {



        try {
            // Convertir la ruta de la cadena a un objeto Path
            Path path = Paths.get(body.path());
            System.out.println(path);
            // Verificar si el archivo existe
            if (Files.exists(path)) {
                // Borrar el archivo
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

                // Obtener la fecha de creación, modificación y acceso
                String creationTime = attr.creationTime().toString();
                String lastModifiedTime = attr.lastModifiedTime().toString();
                String lastAccessTime = attr.lastAccessTime().toString();

                // Obtener el tamaño del archivo
                long size = attr.size();

                // Obtener el tipo de archivo
                String fileType = Files.probeContentType(path);

//                Files.delete(path);
                return "El archivo " + body.path() + " ha sido borrado exitosamente.\n" +
                        "Fecha de creación: " + creationTime + "\n" +
                        "Última modificación: " + lastModifiedTime + "\n" +
                        "Último acceso: " + lastAccessTime + "\n" +
                        "Tamaño: " + size + " bytes\n" +
                        "Tipo de archivo: " + fileType;


            } else {
                return "El archivo " + body.path() + " no existe.";
            }
        } catch (IOException e) {
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
