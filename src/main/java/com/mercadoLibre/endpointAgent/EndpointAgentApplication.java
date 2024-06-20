package com.mercadoLibre.endpointAgent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadoLibre.endpointAgent.models.Client;
import com.mercadoLibre.endpointAgent.repositories.ClientRepository;
import com.mercadoLibre.endpointAgent.services.ScanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class EndpointAgentApplication {

	@Autowired
	private ScanResultService scanResultService;
@Autowired
private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EndpointAgentApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
		return args -> {

//			Client user = new Client("user@meli.com", passwordEncoder.encode("123"));
//
//			clientRepository.save(user);



		};
	}
}
