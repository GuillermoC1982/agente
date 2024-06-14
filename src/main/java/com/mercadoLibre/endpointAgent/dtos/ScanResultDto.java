package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.ScanResult;

import java.time.LocalDateTime;

public class ScanResultDto {

    private String details;
    private boolean isMalicious;
    private LocalDateTime scanDate;

    public ScanResultDto(ScanResult scanResult) {
        this.details = scanResult.getDetails();
        this.isMalicious = scanResult.isMalicious();
        this.scanDate = scanResult.getScanDate();
    }

    public String getDetails() {
        return details;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public LocalDateTime getScanDate() {
        return scanDate;
    }
}
