package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ClientDto;
import com.mercadoLibre.endpointAgent.models.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientRepository clientRepository;

    @Operation(summary = "Endpoint para obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados en el sistema")
    @SecurityRequirement(name = "bearer Authentication")
    @GetMapping("/getClients")
    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }

    @Operation(summary = "Endpoint para registrar un nuevo usuario", description = " Guarda un nuevo usuario en la base de datos sqLite. Retorna este nuevo usuario registrado en el sistema junto con el http status de la operación")
    @PostMapping
    public ResponseEntity<?> createClient(@RequestParam String email, @RequestParam String password){

        if(email == null || password == null){
            return new ResponseEntity<>("Email and password are required", HttpStatus.BAD_REQUEST);
        }

        if(clientRepository.findByEmail(email) != null){
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Client client = new Client(email, passwordEncoder.encode(password));
        clientRepository.save(client);

        return new ResponseEntity<>(new ClientDto(client), HttpStatus.CREATED);
    }

}
