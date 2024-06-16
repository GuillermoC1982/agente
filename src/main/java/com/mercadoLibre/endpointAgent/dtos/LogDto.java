package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.Log;

import java.time.LocalDateTime;

public class LogDto {

    private String action;
    private String details;
    private LocalDateTime date;

    private FileDto file;


    public LogDto(Log log) {
        this.action = log.getAction();
        this.details = log.getDetails();
        this.date = log.getDate();
        this.file = new FileDto(log.getFile());
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getDate() {
        return date;
    }
}