package com.mercadoLibre.endpointAgent.contollers;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.context.SecurityContextHolder;
import com.mercadoLibre.endpointAgent.dtos.ClientDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Operation(summary = "Endpoint para obtener todos los usuarios con el usuario autenticado",
            description = "Retorna una lista con todos los usuarios registrados en el sistema generando un log con la peticion del usuario" +
                    " pero para hacerlo previamente debe recibir el token de autenticacion JWT")
    @SecurityRequirement(name = "bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o no autorizado", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<?>getClients(){

        try{
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);

            if (client == null) {
                return new ResponseEntity<>("User not found or not authenticated", HttpStatus.NOT_FOUND);
            }

            logService.getAllClients(client);

            Set<ClientDto> clients = clientService.findAllClientsDto(clientService.findAllClients());
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("User not found or not authorized", HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Endpoint para registrar un nuevo usuario en el sistema",
            description = " Guarda un nuevo usuario en la base de datos sqLite. Retorna un JSON con este nuevo usuario registrado en el sistema junto " +
                    "con el http status de la operación correspondiente generando el primer log del usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida porque no envio un email o una contraseña", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o no autorizado en el sistema", content = @Content),
            @ApiResponse(responseCode = "409", description = "Este email ya existe en el sistema", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestParam String email, @RequestParam String password){

        try{
            if(email == null || password == null){
                return new ResponseEntity<>("Email and password are required", HttpStatus.BAD_REQUEST);
            }

            if(clientService.findClientByEmail(email) != null){
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            }

            Client client = new Client(email, passwordEncoder.encode(password));

            clientService.saveClient(client);
            logService.createdClient(client);

            return new ResponseEntity<>(new ClientDto(client), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
