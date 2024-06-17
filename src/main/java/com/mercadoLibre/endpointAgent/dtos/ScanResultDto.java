package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.ScanResult;

import java.time.LocalDateTime;

public class ScanResultDto {

    private String details;

    private LocalDateTime scanDate;

    public ScanResultDto(ScanResult scanResult) {
        this.details = scanResult.getDetails();
        this.scanDate = scanResult.getScanDate();
    }

    public String getDetails() {
        return details;
    }


    public LocalDateTime getScanDate() {
        return scanDate;
    }
}
