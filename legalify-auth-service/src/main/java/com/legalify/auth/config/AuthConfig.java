package com.legalify.auth.config;

import com.legalify.auth.dto.AuthLoginRequestDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.legalify.auth.dto.AuthLoginRequestDTO.*;
import static com.legalify.auth.utils.Constants.AUTH_API_ROLES_URI;
import static com.legalify.auth.utils.Constants.AUTH_API_USERS_URI;
import static com.legalify.auth.utils.Constants.AUTH_API_USER_ROLES_URI;
import static com.legalify.auth.utils.Constants.TOKEN_URI;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {

    private String clientId;
    private String clientSecret;
    private String domain;
    private String audience;

    public String getTokenUri() {
        return String.format(TOKEN_URI, domain);
    }

    public String getApiUsersUri() {
        return String.format(AUTH_API_USERS_URI, domain);
    }

    public String getApiUserRolesUri(String userId) {
        return String.format(AUTH_API_USER_ROLES_URI, domain, userId);
    }

    public String getApiRoles() {
        return String.format(AUTH_API_ROLES_URI, domain);
    }

    public AuthLoginRequestDTOBuilder getBaseAuthRequest() {
        return AuthLoginRequestDTO.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .audience(audience);
    }
}
