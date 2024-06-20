package com.mercadoLibre.endpointAgent.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.mercadoLibre.endpointAgent.models.ScanResult;

public interface ScanResultService {

   void saveScanResult(ScanResult scanResult);

   JsonNode createJsonByString(String str);
}
