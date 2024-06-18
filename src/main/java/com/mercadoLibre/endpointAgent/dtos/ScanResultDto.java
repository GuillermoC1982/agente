package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.ScanResult;

import java.time.LocalDateTime;

public class ScanResultDto {
    private LocalDateTime scanDate;
    private String details;



    public ScanResultDto(ScanResult scanResult) {
        this.scanDate = scanResult.getScanDate();
        this.details = scanResult.getDetails();

    }

    public String getDetails() {
        return details;
    }


    public LocalDateTime getScanDate() {
        return scanDate;
    }
}
