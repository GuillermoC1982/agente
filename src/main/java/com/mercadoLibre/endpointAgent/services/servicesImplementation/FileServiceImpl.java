package com.mercadoLibre.endpointAgent.services.servicesImplementation;

import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.repositories.FileRepository;
import com.mercadoLibre.endpointAgent.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void saveFile(File file) {
        fileRepository.save(file);
    }

    @Override
    public File findFileBySha256(String sha256) {
        return fileRepository.findFileBySha256(sha256);
    }

    @Override
    public File generateFileByBasicFileAttributes(BasicFileAttributes attributes, Path path) {
        try {
            String creationTime = attributes.creationTime().toString();
            String lastModifiedTime = attributes.lastModifiedTime().toString();
            String lastAccessTime = attributes.lastAccessTime().toString();
            long size = attributes.size();
            String fileType = Files.probeContentType(path);

            File file = new File();
            file.setCreationTime( stringToLocalDateTime(creationTime));
            file.setLastModifiedTime(stringToLocalDateTime(lastModifiedTime));
            file.setLastAccessTime(stringToLocalDateTime(lastAccessTime));
            file.setSizeInBytes(size);
            file.setFileType(fileType);
            file.setPath(path.toString());
            file.setSha256(calculateSha256(path.toString()));
            return file;

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public LocalDateTime stringToLocalDateTime(String date) {
        // Convierte primero la cadena en una ZonedDateTime porque incluye la información de la zona horaria (UTC)
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, DateTimeFormatter.ISO_ZONED_DATE_TIME);

        // Convertir a LocalDateTime en la zona horaria del sistema
        LocalDateTime localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        return localDateTime;
    }

    // Calcular el hash SHA256 de un archivo
    @Override
    public String calculateSha256(String filePath) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(filePath);
            byte[] byteArray = new byte[8192];
            int bytesCount;

            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }

            fis.close();
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (IOException | NoSuchAlgorithmException e) {
            System.out.println("Error calculando SHA256: " + e.getMessage());
            e.printStackTrace();
            return "Error calculating SHA256 for file: " + filePath;
        }

    }

    // Enviar el hash de un archivo a VirusTotal y retornar la respuesta de la API

    @Override
    public String sendHashToVirustotal(String hashFile, String apiKey) {
        try {
            String url = "https://www.virustotal.com/api/v3/files/" + hashFile;
            URL obj = new URL(url);
            HttpURLConnection conection = (HttpURLConnection) obj.openConnection();

            // Configurar la solicitud HTTP a la API de VirusTotal
            conection.setRequestMethod("GET");
            conection.setRequestProperty("x-apikey", apiKey);

            int responseCode = conection.getResponseCode();

            // Valido si la solicitud es exitosa o no
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("Error en la solicitud: " + responseCode);
                return "Error in the request made: " + responseCode;
            }
        } catch (IOException e) {
            System.out.println("Error enviando hash a VirusTotal: " + e.getMessage());
            e.printStackTrace();
            return "Error sending hash to VirusTotal: " + e.getMessage();
        }
    }


}
