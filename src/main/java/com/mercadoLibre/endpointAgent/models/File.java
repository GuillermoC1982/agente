package com.mercadoLibre.endpointAgent.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;

    private String permissions;

    private String sha256;

    private LocalDateTime creationTime;

    private LocalDateTime lastModifiedTime;

    private LocalDateTime lastAccessTime;

    @OneToMany(mappedBy = "file")
    private Set<ScanResult> scanResults = new HashSet<>();

    @OneToMany(mappedBy = "file")
    private Set<Log> logs = new HashSet<>();

    public File() {
    }

    public File(String path, String permissions, String sha256, LocalDateTime creationTime, LocalDateTime lastModifiedTime, LocalDateTime accessedAt) {
        this.path = path;
        this.permissions = permissions;
        this.sha256 = sha256;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
        this.lastAccessTime = accessedAt;
    }

    public Set<ScanResult> getScanResults() {
        return scanResults;
    }

    public void setScanResults(Set<ScanResult> scanResults) {
        this.scanResults = scanResults;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public long getId() {
        return id;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}
