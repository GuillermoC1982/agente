package com.mercadoLibre.endpointAgent.services.servicesImplementation;
import com.mercadoLibre.endpointAgent.dtos.LogDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.File;
import com.mercadoLibre.endpointAgent.models.Log;
import com.mercadoLibre.endpointAgent.models.ScanResult;
import com.mercadoLibre.endpointAgent.repositories.LogRepository;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.FileService;
import com.mercadoLibre.endpointAgent.services.LogService;
import com.mercadoLibre.endpointAgent.services.ScanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    private ScanResultService scanResultService;
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
        log.setAction("Client Created");
        log.setDetails("Client with email '" + client.getEmail() + "' was created successfully");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        client.addLog(log);

        clientService.saveClient(client);

    }

    @Override
    public void loggedClient(Client client) {
        Log log = new Log();
        log.setAction("Client Logged");
        log.setDetails(client.getEmail() + " 'was logged successfully in the system right now'");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);

        client.addLog(log);

        clientService.saveClient(client);
    }

    @Override
    public void deletedFile(File file, Client client) {
        Log log = new Log();
        log.setAction("File Deleted");
        log.setDetails(file.getPath() + " 'was deleted successfully'");
        log.setDate(LocalDateTime.now());
            logRepository.save(log);
            file.addLog(log);
            fileService.saveFile(file);
            client.addLog(log);
            clientService.saveClient(client);
    }

    @Override
    public void getAllClients(Client client) {
        Log log = new Log();
        log.setAction("GetAllClients");
        log.setDetails("GetAllClients 'was executed successfully all clients were retrieved'");
        log.setDate(LocalDateTime.now());
            logRepository.save(log);
            client.addLog(log);
            clientService.saveClient(client);
    }

    @Override
    public Set<LogDto> getAllLogs(Client client) {
        Log log = new Log();
        log.setAction("GetAllLogs");
        log.setDetails("GetAllLogs 'was executed successfully all logs were retrieved'");
        log.setDate(LocalDateTime.now());
        logRepository.save(log);
        client.addLog(log);
        clientService.saveClient(client);
        return logRepository.findAll().stream().map(LogDto::new).collect(Collectors.toSet());
    }


    @Override
    public void scanFile(File file, Client client, ScanResult scanResult) {

        Log log = new Log();
        log.setAction("Scan File");
        log.setDetails(file.getPath() + " 'was scanned successfully'");
        log.setDate(LocalDateTime.now());
            logRepository.save(log);
            file.addLog(log);
            fileService.saveFile(file);
            file.addScanResult(scanResult);
            scanResultService.saveScanResult(scanResult);
            client.addLog(log);
            clientService.saveClient(client);


    }
}
