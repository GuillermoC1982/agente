package com.mercadoLibre.endpointAgent.servicesSecutiry;

import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);

        if(client == null){
            throw new UsernameNotFoundException(username);
        }


        return User
                .withUsername(username)
                .password(client.getPasword())
                .roles("CLIENT")
                .build();
    }
}
