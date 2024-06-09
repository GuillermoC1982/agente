package com.mercadoLibre.endpointAgent.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;

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
