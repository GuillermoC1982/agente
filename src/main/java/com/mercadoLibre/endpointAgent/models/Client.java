package com.mercadoLibre.endpointAgent.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Modelo que representa un Usuario")
@Entity
public class Client {
    @Schema(description = "El ID del Usuario", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "El email del Usuario", example = "guillermo.cornetti@gmail.com")
    private String email;

    @Schema(description = "El password del Usuario", example = "password")
    private String pasword;

    @OneToMany(mappedBy = "client")
    private Set<Log> logs = new HashSet<>();

    public Client() {
    }

    public Client(String email, String pasword) {
        this.email = email;
        this.pasword = pasword;
    }
    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
