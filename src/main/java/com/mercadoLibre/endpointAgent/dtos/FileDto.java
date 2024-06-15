package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.File;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class FileDto {

    private String path;
    private String permissions;
    private String sha256;
    private LocalDateTime creationTime;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime lastAccessTime;

    private Set<ScanResultDto> scanResults;

    public FileDto(File file) {
        this.path = file.getPath();
        this.permissions = file.getPermissions();
        this.sha256 = file.getSha256();
        this.creationTime = file.getCreationTime();
        this.lastModifiedTime = file.getLastModifiedTime();
        this.lastAccessTime = file.getLastAccessTime();
        this.scanResults = file.getScanResults().stream().map(scanResult -> new ScanResultDto(scanResult)).collect(Collectors.toSet());
    }

    public String getPath() {
        return path;
    }

    public String getPermissions() {
        return permissions;
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
}
