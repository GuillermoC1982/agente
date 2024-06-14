package com.mercadoLibre.endpointAgent.dtos;

import com.mercadoLibre.endpointAgent.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private String email;
    private String password;

    private Set<LogDto> logs = new HashSet<>();

    public ClientDto(Client client) {
        this.email = client.getEmail();
        this.password = client.getPasword();
        this.logs = client.getLogs().stream().map(log -> new LogDto(log)).collect(Collectors.toSet());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<LogDto> getLogs() {
        return logs;
    }
}
