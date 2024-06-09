package com.mercadoLibre.endpointAgent.contollers;

import com.mercadoLibre.endpointAgent.repositories.ClientRepository;
import com.mercadoLibre.endpointAgent.servicesSecutiry.JwtUtilService;
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
    ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestParam String email, @RequestParam String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String jwt = jwtUtilService.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        }catch (Exception e){
            return new ResponseEntity<>("Email or password invalid" , HttpStatus.BAD_REQUEST);
        }
    }
}
