package com.mercadoLibre.endpointAgent.contollers;
import com.mercadoLibre.endpointAgent.dtos.ClientDto;
import com.mercadoLibre.endpointAgent.dtos.LogDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private LogService logService;


    @Operation(summary = "Endpoint para obtener todos los logs del sistema con el usuario autenticado",
            description = "Retorna una lista con todos los logs registrados en el sistema generando un log con la peticion del usuario")
    @SecurityRequirement(name = "bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de logs obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LogDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o no autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllLogs() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);

            if (client == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(logService.getAllLogs(client));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Endpoint para obtener todos los logs del usuario autenticado",
            description = "Retorna un JSON con todos los logs registrados del usuario autenticado generando un log con la peticion del mismo en la base de datos")
    @SecurityRequirement(name = "bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de logs obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o no autotenciado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al obtener los logs", content = @Content)
    })
    @GetMapping("/currentClient")
    public ResponseEntity<?> getCurrentClientLogs() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);
            if (client == null) {
                return new ResponseEntity<>("User is not found or not authenticated", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ClientDto(client), HttpStatus.OK);
        }

        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }}
}