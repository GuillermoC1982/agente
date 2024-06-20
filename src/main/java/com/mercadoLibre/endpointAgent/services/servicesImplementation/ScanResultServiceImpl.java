package com.mercadoLibre.endpointAgent.services.servicesImplementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadoLibre.endpointAgent.models.ScanResult;
import com.mercadoLibre.endpointAgent.repositories.ScanResultRepository;
import com.mercadoLibre.endpointAgent.services.ScanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanResultServiceImpl implements ScanResultService {

    @Autowired
    private ScanResultRepository scanResultRepository;
    @Override
    public void saveScanResult(ScanResult scanResult) {
        scanResultRepository.save(scanResult);
    }

    @Override
    public JsonNode createJsonByString(String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(string);
            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
