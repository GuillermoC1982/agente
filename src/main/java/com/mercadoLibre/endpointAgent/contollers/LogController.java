package com.mercadoLibre.endpointAgent.contollers;
import com.mercadoLibre.endpointAgent.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/getAll")
    public String getAllLogs(){
        return logService.getAllLogs().toString();
    }

}