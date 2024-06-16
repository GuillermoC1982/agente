package com.mercadoLibre.endpointAgent.services.servicesImplementation;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;
import com.mercadoLibre.endpointAgent.repositories.LogRepository;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.FileService;
import com.mercadoLibre.endpointAgent.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ClientService clientService;


    @Override
    public void saveLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public void createdClient(Client client) {
        Log log = new Log();
        log.setAction("Created");
        log.setDetails("Client with email '" + client.getEmail() + "' was created successfully");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        client.addLog(log);

        clientService.saveClient(client);

    }

    @Override
    public void loggedClient(Client client) {
        Log log = new Log();
        log.setAction("Logged");
        log.setDetails(client.getEmail() + " was logged successfully in the system right now");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        client.addLog(log);

        clientService.saveClient(client);
    }

    @Override
    public void deletedFile(File file) {
        Log log = new Log();
        log.setAction("Deleted");
        log.setDetails(file.getPath() + " was deleted successfully");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        file.addLog(log);

        fileService.saveFile(file);
    }

    @Override
    public void getAllClients(Client client) {
        Log log = new Log();
        log.setAction("GetAllClients");
        log.setDetails("GetAllClients was executed successfully all clients were retrieved");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        client.addLog(log);

        clientService.saveClient(client);
    }
}
