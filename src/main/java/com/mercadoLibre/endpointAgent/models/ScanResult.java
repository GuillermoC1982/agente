package com.mercadoLibre.endpointAgent.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String details;
    private LocalDateTime scanDate;

    @ManyToOne
    private File file;

    public ScanResult() {
    }

    public ScanResult(String details,  LocalDateTime scanDate) {
        this.details = details;
        this.scanDate = scanDate;
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
