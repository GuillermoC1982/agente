package com.mercadoLibre.endpointAgent.contollers;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Endpoint para obtener todos los logs del sistema",
            description = "Retorna una lista con todos los logs registrados en el sistema generando un log con la peticion del usuario" )
    @SecurityRequirement(name = "bearer Authentication")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllLogs(){
        try{
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.findClientByEmail(email);

            if (client == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(logService.getAllLogs(client));
        }

        catch (Exception e){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }



    }

}
