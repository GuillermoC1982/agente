package com.mercadoLibre.endpointAgent.services;

import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;

public interface LogService {

    void saveLog(Log log);
    void createdClient(Client client);

    void loggedClient(Client client);

    void deletedFile(File file);

    void getAllClients(Client client);

}
