package com.mercadoLibre.endpointAgent.services;

import com.mercadoLibre.endpointAgent.dtos.LogDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;

import java.util.Set;

public interface LogService {

    void saveLog(Log log);
    void createdClient(Client client);

    void loggedClient(Client client);

    void deletedFile(File file, Client client);

    void getAllClients(Client client);

    Set<LogDto> getAllLogs(Client client);

    void scanFile(File file, Client client);



}
