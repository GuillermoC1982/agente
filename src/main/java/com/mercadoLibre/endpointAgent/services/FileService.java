package com.mercadoLibre.endpointAgent.services;

public interface FileService {
     String calculateSha256(String filePath);

     String sendHashToVirustotal(String hashFile, String apiKey);
}
