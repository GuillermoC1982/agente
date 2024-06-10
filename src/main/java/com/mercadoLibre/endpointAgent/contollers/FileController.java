package com.mercadoLibre.endpointAgent.contollers;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
public class FileController {


//Recordar enviar la ruta del archivo con la notación de doble barra (//)
// para evitar problemas con los caracteres de escape

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam String filePath) {
        //String filePath = "E:\\Guillo\\Mindhub.jpeg";
        try {
            // Convertir la ruta de la cadena a un objeto Path
            Path path = Paths.get(filePath);

            // Verificar si el archivo existe
            if (Files.exists(path)) {
                // Borrar el archivo
                Files.delete(path);
                return "El archivo " + filePath + " ha sido borrado exitosamente.";
            } else {
                return "El archivo " + filePath + " no existe.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al intentar borrar el archivo " + filePath + ": " + e.getMessage();
        }
    }

    @GetMapping("/getFiles")
    public String getFiles(){
        return "Files";
    }
}
