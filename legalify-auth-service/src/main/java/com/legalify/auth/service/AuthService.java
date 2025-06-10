package com.legalify.auth.service;

import com.legalify.auth.config.AuthConfig;
import com.legalify.auth.dto.AuthLoginRequestDTO;
import com.legalify.auth.dto.AuthRequestDTO;
import com.legalify.auth.model.LoginRequest;
import com.legalify.auth.model.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.legalify.auth.utils.Constants.LOGIN_SCOPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.PASSWORD;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthConfig authConfig;

    public LoginResponse authenticateUser(LoginRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                authConfig.getTokenUri(),
                getHttpEntity(request),
                LoginResponse.class
        );
        return response.getBody();
    }

    private HttpEntity<AuthRequestDTO> getHttpEntity(LoginRequest loginRequest) {
        AuthLoginRequestDTO request = (AuthLoginRequestDTO) authConfig.getBaseAuthRequest()
                .password(loginRequest.getPassword())
                .username(loginRequest.getEmail())
                .domain(authConfig.getDomain())
                .scope(LOGIN_SCOPE)
                .grantType(PASSWORD)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

}
