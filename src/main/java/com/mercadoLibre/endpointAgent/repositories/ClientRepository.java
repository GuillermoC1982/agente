package com.mercadoLibre.endpointAgent.repositories;

import com.mercadoLibre.endpointAgent.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
}
