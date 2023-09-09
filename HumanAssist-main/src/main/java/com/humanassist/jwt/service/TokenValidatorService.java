package com.humanassist.jwt.service;

import com.humanassist.domain.JWTClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TokenValidatorService {
    private final Logger LOGGER = Logger.getLogger(TokenValidatorService.class.getCanonicalName());
    @Value("${humanassist.registration-service.endpoint}")
    private String REGISTRATION_SERVICE_ENDPOINT;

    @Autowired
    private RestTemplate restTemplate;


    public JWTClaims validateJWToken(String token) throws Exception {
        LOGGER.log(Level.INFO, "Going to validate token and get Claims from AuthenticationService, and the endpoint is ::: " + REGISTRATION_SERVICE_ENDPOINT);

        //String url = "http://localhost:8080/validate";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Authorization", "Bearer " +  token);
        String requestBody = token;
        HttpEntity<String> entity = new HttpEntity<>(requestBody, header);
        ResponseEntity<JWTClaims> response = restTemplate.postForEntity(REGISTRATION_SERVICE_ENDPOINT, entity, JWTClaims.class);
        LOGGER.log(Level.INFO, "Response is ::: " + response.getBody());
        LOGGER.log(Level.INFO, "Successfully validated token...");
        if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            LOGGER.log(Level.SEVERE, "Token seems to be invalid....");
            throw new Exception("Token seems to be invalid");
        }
        return response.getBody();
    }
}
