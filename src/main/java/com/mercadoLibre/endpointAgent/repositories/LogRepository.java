package com.mercadoLibre.endpointAgent.repositories;

import com.mercadoLibre.endpointAgent.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
