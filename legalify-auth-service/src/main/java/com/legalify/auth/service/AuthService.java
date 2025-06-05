package com.legalify.auth.service;

import com.legalify.auth.dto.AuthRequestDTO;
import com.legalify.auth.model.LoginRequest;
import com.legalify.auth.model.LoginResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@Service
public class AuthService {

    private static final String TOKEN_URI = "https://%s/oauth/token";

    private final Environment env;

    public AuthService(Environment env) {
        this.env = env;
    }

    public LoginResponse authenticateUser(LoginRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                getTokenUri(),
                HttpMethod.POST,
                getHttpEntity(request),
                LoginResponse.class
        );

        return response.getBody();
    }

    private HttpEntity<AuthRequestDTO> getHttpEntity(LoginRequest loginRequest) {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setClientId(env.getProperty("AUTH0_CLIENT_ID"));
        request.setClientSecret(env.getProperty("AUTH0_CLIENT_SECRET"));
        request.setDomain(env.getProperty("AUTH0_DOMAIN"));
        request.setAudience(env.getProperty("AUTH0_AUDIENCE"));
        request.setGrantType(env.getProperty("AUTH0_GRANT_TYPE"));
        request.setScope(env.getProperty("AUTH0_SCOPE"));

        request.setUsername(loginRequest.getEmail());
        request.setPassword(loginRequest.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

    private String getTokenUri() {
        return String.format(TOKEN_URI, env.getProperty("AUTH0_DOMAIN"));
    }
}
