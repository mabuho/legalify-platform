package com.legalify.auth.service;

import com.legalify.auth.config.AuthConfig;
import com.legalify.auth.dto.AuthLoginRequestDTO;
import com.legalify.auth.dto.AuthRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static com.nimbusds.oauth2.sdk.GrantType.CLIENT_CREDENTIALS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.ACCESS_TOKEN;

@Service
@AllArgsConstructor
public class AuthManagementTokenService {

    private final AuthConfig authConfig;

    // TODO: Should this method be cached?
    public String getManagementToken() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(
                authConfig.getTokenUri(),
                POST,
                getHttpEntity(),
                Map.class
        );
        return Objects.requireNonNull(response.getBody()).get(ACCESS_TOKEN).toString();
    }

    private HttpEntity<AuthRequestDTO> getHttpEntity() {
        AuthLoginRequestDTO request = (AuthLoginRequestDTO) authConfig
                .getBaseAuthRequest()
                .grantType(CLIENT_CREDENTIALS.getValue())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }



}
