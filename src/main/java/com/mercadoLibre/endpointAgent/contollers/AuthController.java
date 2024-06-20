package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.dtos.TokenDto;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.models.JwtResponse;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;
import com.mercadoLibre.endpointAgent.services.ClientService;
import com.mercadoLibre.endpointAgent.services.LogService;
import com.mercadoLibre.endpointAgent.servicesSecutiry.JwtUtilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private ClientService clientService;

    @Autowired
    private LogService logService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Operation(summary = "Endpoint para loguear al usuario", description = "Retorna un token jwt para el usuario que se loguea. " +
            "Este token luego debe enviarse con cada peticion realizada en la app para que la peticion sea procesada. " +
            "En caso de error retorna un mensaje de error con el error correspondiente en el body de la respuesta")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))
            }),
            @ApiResponse(responseCode = "403", description = "Credenciales inválidas", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestParam String email, @RequestParam String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String jwt = jwtUtilService.generateToken(userDetails);

            logService.loggedClient(clientService.findClientByEmail(email));


            return new ResponseEntity<>( new TokenDto(jwt), HttpStatus.OK );

        }catch (Exception e){
            return new ResponseEntity<>("Email or password invalid" , HttpStatus.FORBIDDEN);
        }
    }
}
