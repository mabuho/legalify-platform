package com.legalify.auth.service;

import com.legalify.auth.config.AuthConfig;
import com.legalify.auth.dto.AssignRoleRequestDTO;
import com.legalify.auth.dto.AuthRoleDTO;
import com.legalify.auth.model.SignupResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class AuthRoleService {

    private final AuthConfig authConfig;

    public List<AuthRoleDTO> getAllRoles(String managementToken) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<AuthRoleDTO>> response = restTemplate.exchange(
                authConfig.getApiRoles(),
                GET,
                getRolesHttpEntity(managementToken),
                new ParameterizedTypeReference<>() {}
        );
        if(response.hasBody() && response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error al obtener los roles.");
        }
    }

    public void assignRoleToUser(SignupResponse user, String role, String managementToken) {
        RestTemplate restTemplate = new RestTemplate();
        AuthRoleDTO authRole = getAuthRoleByName(managementToken, role);

        ResponseEntity<String> response = restTemplate.postForEntity(
                authConfig.getApiUserRolesUri(user.getUserId()),
                getHttpEntity(authRole, managementToken),
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al asignar el role=" + authRole.getName() + " al usuario=" + user.getUserId() + ".");
        }
    }

    private HttpEntity<?> getRolesHttpEntity(String managementToken) {
        HttpHeaders headers = getHeaders(managementToken);
        headers.setAccept(List.of(APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }

    private HttpEntity<AssignRoleRequestDTO> getHttpEntity(AuthRoleDTO role, String managementToken) {
        HttpHeaders headers = getHeaders(managementToken);
        headers.setContentType(APPLICATION_JSON);

        AssignRoleRequestDTO dto = AssignRoleRequestDTO.builder()
                .roles(List.of(role.getId()))
                .build();

        return new HttpEntity<>(dto, headers);
    }

    private HttpHeaders getHeaders(String managementToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(managementToken);
        return headers;
    }

    private AuthRoleDTO getAuthRoleByName(String managementToken, final String roleName) {
        List<AuthRoleDTO> roles = getAllRoles(managementToken);
        return roles.stream()
                .filter(r -> r.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El rol=" + roleName + " no existe."));
    }

}
