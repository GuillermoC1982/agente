package com.mercadoLibre.endpointAgent.repositories;

import com.mercadoLibre.endpointAgent.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
