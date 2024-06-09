package com.mercadoLibre.endpointAgent.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String details;
    private boolean isMalicious;
    private LocalDateTime scanDate;

    @ManyToOne
    private File file;

    public ScanResult() {
    }

    public ScanResult(String details, boolean isMalicious, LocalDateTime scanDate, File file) {
        this.details = details;
        this.isMalicious = isMalicious;
        this.scanDate = scanDate;
        this.file = file;
    }

    public long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public void setMalicious(boolean malicious) {
        isMalicious = malicious;
    }

    public LocalDateTime getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDateTime scanDate) {
        this.scanDate = scanDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
