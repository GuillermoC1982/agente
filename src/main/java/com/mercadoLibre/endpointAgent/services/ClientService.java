package com.mercadoLibre.endpointAgent.services;

import com.mercadoLibre.endpointAgent.dtos.ClientDto;
import com.mercadoLibre.endpointAgent.models.Client;

import java.util.List;
import java.util.Set;

public interface ClientService {

    void saveClient(Client client);

    Client findClientByEmail(String email);

    List<Client> findAllClients();

    Set<ClientDto> findAllClientsDto(List<Client> clients);
}
