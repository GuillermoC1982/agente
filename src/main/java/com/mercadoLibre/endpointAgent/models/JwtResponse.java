package com.mercadoLibre.endpointAgent.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa la respuesta con un JWT Token")
public class JwtResponse {
    @Schema(description = "El JWT Token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
