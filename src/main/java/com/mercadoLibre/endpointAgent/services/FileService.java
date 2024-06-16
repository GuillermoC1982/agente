package com.mercadoLibre.endpointAgent.services;

import com.mercadoLibre.endpointAgent.models.File;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;

public interface FileService {

     void saveFile(File file);
     LocalDateTime stringToLocalDateTime(String date);
     String calculateSha256(String filePath);

     String sendHashToVirustotal(String hashFile, String apiKey);

     File findFileBySha256(String sha256);

     File generateFileByBasicFileAttributes(BasicFileAttributes attributes, Path path);

}
