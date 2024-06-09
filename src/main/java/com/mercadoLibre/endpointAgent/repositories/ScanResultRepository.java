package com.mercadoLibre.endpointAgent.repositories;

import com.mercadoLibre.endpointAgent.models.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanResultRepository extends JpaRepository<ScanResult, Long> {
}
