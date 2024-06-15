package com.mercadoLibre.endpointAgent.services.servicesImplementation;

import com.mercadoLibre.endpointAgent.services.FileService;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileServiceImpl implements FileService {

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
        } catch (IOException | NoSuchAlgorithmException e) {

            e.printStackTrace();
            return null;
        }

    }


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
            if (responseCode == HttpURLConnection.HTTP_OK) { // Si la solicitud es exitosa
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
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
