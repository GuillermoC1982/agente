package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.ClientDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private LogService logService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Endpoint para obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados en el sistema generando un log con la peticion del usuario")
    @SecurityRequirement(name = "bearer Authentication")
    @GetMapping("/getAll")
    public ResponseEntity<?>getClients(){
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Operation(summary = "Endpoint para registrar un nuevo usuario", description = " Guarda un nuevo usuario en la base de datos sqLite. Retorna este nuevo usuario registrado en el sistema junto con el http status de la operación correspondiente generando el primer log del usuario")
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestParam String email, @RequestParam String password){

        if(email == null || password == null){
            return new ResponseEntity<>("Email and password are required", HttpStatus.BAD_REQUEST);
        }

        if(clientService.findClientByEmail(email) != null){
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Client client = new Client(email, passwordEncoder.encode(password));

        clientService.saveClient(client);


        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

}
