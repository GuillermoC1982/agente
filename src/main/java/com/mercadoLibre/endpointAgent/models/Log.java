package com.mercadoLibre.endpointAgent.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String action;

    private String details;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    public Log() {
    }

    public Log(String action, String details, LocalDateTime date) {
        this.action = action;
        this.details = details;
        this.date = date;
    }

    public long getId() {
        return id;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public File getFile() {return file; }

    public void setFile(File file) {
        this.file = file;
    }


}
