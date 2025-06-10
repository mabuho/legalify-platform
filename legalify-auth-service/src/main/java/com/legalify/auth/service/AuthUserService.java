package com.legalify.auth.service;

import com.legalify.auth.config.AuthConfig;
import com.legalify.auth.dto.AuthSignupRequestDTO;
import com.legalify.auth.dto.SignupRequestDTO;
import com.legalify.auth.model.SignupResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.legalify.auth.utils.Constants.UP_CONNECTION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class AuthUserService {

    private final AuthConfig authConfig;

    public SignupResponse createUser(SignupRequestDTO dto, String managementToken) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SignupResponse> response = restTemplate.postForEntity(
                authConfig.getApiUsersUri(),
                getHttpEntity(managementToken, dto),
                SignupResponse.class
        );
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            // User created!!
            System.out.println(response.getBody());
            return response.getBody();
        } else {
            throw new RuntimeException("Error al crear el usuario en Auth0");
        }
    }

    private HttpEntity<AuthSignupRequestDTO> getHttpEntity(String managementToken, SignupRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(managementToken);
        headers.setContentType(APPLICATION_JSON);
        AuthSignupRequestDTO signupRequest = AuthSignupRequestDTO.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .connection(UP_CONNECTION)
                .build();
        return new HttpEntity<>(signupRequest, headers);
    }

}
