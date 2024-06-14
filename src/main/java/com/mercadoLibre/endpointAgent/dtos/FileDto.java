package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.ScanResult;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class FileDto {

    private String path;
    private String permissions;
    private String sha256;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime accessedAt;

    private Set<ScanResultDto> scanResults;

    public FileDto(File file) {
        this.path = file.getPath();
        this.permissions = file.getPermissions();
        this.sha256 = file.getSha256();
        this.createAt = file.getCreateAt();
        this.modifiedAt = file.getModifiedAt();
        this.accessedAt = file.getAccessedAt();
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public LocalDateTime getAccessedAt() {
        return accessedAt;
    }
}
