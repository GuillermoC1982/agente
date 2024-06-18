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

//    String creationTime = attr.creationTime().toString();
//    String lastModifiedTime = attr.lastModifiedTime().toString();
//    String lastAccessTime = attr.lastAccessTime().toString();
//    long size = attr.size();
//    String fileType = Files.probeContentType(path);
    private String path;
    private String fileType;
    private String sha256;

    private LocalDateTime creationTime;

    private LocalDateTime lastModifiedTime;

    private LocalDateTime lastAccessTime;

    private Long sizeInBytes;

    @OneToMany(mappedBy = "file")
    private Set<ScanResult> scanResults = new HashSet<>();

    @OneToMany(mappedBy = "file")
    private Set<Log> logs = new HashSet<>();

    public File() {
    }

    public File(String fileType) {
        this.fileType = fileType;
    }

    public File(String path, String fileType, String sha256, LocalDateTime creationTime, LocalDateTime lastModifiedTime, LocalDateTime lastAccessTime, Long sizeInBytes) {
        this.path = path;
        this.fileType = fileType;
        this.sha256 = sha256;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
        this.lastAccessTime = lastAccessTime;
        this.sizeInBytes = sizeInBytes;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
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

    public void addScanResult(ScanResult scanResult) {
        this.scanResults.add(scanResult);
        scanResult.setFile(this);
    }

    public void addLog(Log log) {
        this.logs.add(log);
        log.setFile(this);
    }

}
