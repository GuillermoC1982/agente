package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ReciveFileDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;
import com.mercadoLibre.endpointAgent.models.ScanResult;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import com.mercadoLibre.endpointAgent.services.ScanResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ScanResultService scanResultService;


    @Autowired
    private ClientService clientService;


//Recordar enviar la ruta del archivo con la notación de doble barra // al enviar valor de filePath
// para evitar problemas con los caracteres de escape


    @Operation(summary = "Endpoint para borrar un archivo en el sistema , registrarlo en el log de la app y en la base de datos sqLite con el usuario autenticado ",
            description = "\"**IMPORTANTE:** usar el formato de doble barra invertida (\\\\) o barra simple (/) en la ruta del archivo al enviar la petición\". <br><br> Retorna los atributos del archivo borrado o un mensaje de error si el archivo no existe en el sistema" +
                    " o el usuario no esta autenticado devuelve un error 403 FORBIDDEN")
    @SecurityRequirement(name = "bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, User is not authenticated or does not have permission to delete the file"),
    })

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestBody ReciveFileDto body) {
        System.out.println(body.path());

        File file;
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);

            if (client != null) {

                // Convertir la ruta de la cadena a un objeto de tipo Path
                Path path = Paths.get(body.path());
                String sha256 = fileService.calculateSha256(body.path());
                System.out.println("SHA256: " + sha256);

                // Verificar si el archivo existe
                if (Files.exists(path)) {
                    // Obtener los atributos del archivo
                    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

                    //Obtengo los atributos del archivo y los guardo en el objeto File para retornarlos en la respuesta de la petición y para registrarlos en la base de datos sqLite y en el log de la app
                    file = fileService.generateFileByBasicFileAttributes(attr, path);
                    logService.deletedFile(file, client);
                    System.out.println("Path: " + path);

                    // Borrar el archivo en el sistema
                    Files.delete(path);
                }else {
                    throw new FileNotFoundException();
                }

            } else {
                return new ResponseEntity<>("User is not authenticated", HttpStatus.FORBIDDEN);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("The system cannot find the specified file: " + body.path() + " : " + e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (AccessDeniedException e) {
            e.printStackTrace();
            return new ResponseEntity<>("User does not have permission to delete the file: " + body.path() + " : " + e.getMessage(), HttpStatus.FORBIDDEN);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during the deletion of the file: " + body.path() + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("The file " + body.path() + " has been deleted successfully.\n" +
                "Date creation: " + file.getCreationTime() + "\n" +
                "Last modified: " + file.getLastModifiedTime() + "\n" +
                "Last access: " + file.getLastAccessTime() + "\n" +
                "Size: " + file.getSizeInBytes() + " bytes\n" +
                "File type: " + file.getFileType() + "\n", HttpStatus.OK);
    }

    @Operation(summary = "Endpoint para escanear un archivo en el sistema ",
            description = "Retorna la respuesta de VirusTotal la cual sera guardada como una propiedad del objeto File, <br><br> En este caso la ruta se manda normalmente sin notación de doble barra ya que se recibe como un requestParam. <br><br> " +
                    "Retorna los atributos del archivo escaneado o un mensaje de error si el archivo no existe en el sistema" )
    @SecurityRequirement(name = "bearer Authentication")
    @GetMapping("/scan")
    public ResponseEntity<?> scanFile(@RequestParam String path) {

        File file;
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);
            Path pathFile = Paths.get(path);

            if (Files.exists(pathFile)) {
                // Obtener los atributos del archivo
                BasicFileAttributes attr = Files.readAttributes(pathFile, BasicFileAttributes.class);

                //Obtengo los atributos del archivo y los guardo en el objeto File para retornarlos en la respuesta de la petición y para registrarlos en la base de datos sqLite y en el log de la app
                file = fileService.generateFileByBasicFileAttributes(attr, pathFile);
                logService.scanFile(file, client);
                System.out.println("Path: " + path);


            }else {
                throw new FileNotFoundException();
            }

        }
        catch (AccessDeniedException e){
            e.printStackTrace();
            return new ResponseEntity<>("User does not have permission to delete the file: " + path + " : " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>("The system cannot find the specified file: " + path + " : " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("User is not authenticated", HttpStatus.FORBIDDEN);
        }

            String apiKey = "bfed34dc176355aa97d52ee5434bc93bf96d1985c5daa52afc47cf7a8c314ab7";



            String hashSha256 = fileService.calculateSha256(path);
            String result = fileService.sendHashToVirustotal(hashSha256, apiKey);

            ScanResult scanResult = new ScanResult(result, LocalDateTime.now());
            file.addScanResult(scanResult);

            fileService.saveFile(file);
            scanResultService.saveScanResult(scanResult);


            System.out.println("El hash SHA-256 del archivo es: " + hashSha256);
            System.out.println("Respuesta de VirusTotal: " + result);

            //return result;

            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
