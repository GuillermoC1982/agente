package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/getClients")
    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }



}
