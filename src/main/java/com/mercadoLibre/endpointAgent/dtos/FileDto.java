package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.File;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class FileDto {

    private String path;
    private String fileType;
    private String sha256;
    private LocalDateTime creationTime;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime lastAccessTime;

    private Long sizeInBytes;

    private Set<ScanResultDto> scanResults;

    public FileDto(File file) {
        this.path = file.getPath();
        this.fileType = file.getFileType();
        this.sha256 = file.getSha256();
        this.creationTime = file.getCreationTime();
        this.lastModifiedTime = file.getLastModifiedTime();
        this.lastAccessTime = file.getLastAccessTime();
        this.sizeInBytes = file.getSizeInBytes();
        this.scanResults = file.getScanResults().stream().map(scanResult -> new ScanResultDto(scanResult)).collect(Collectors.toSet());
    }

    public String getPath() {
        return path;
    }

    public String getFileType() {
        return fileType;
    }

    public String getSha256() {
        return sha256;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public Set<ScanResultDto> getScanResults() {
        return scanResults;
    }
}
