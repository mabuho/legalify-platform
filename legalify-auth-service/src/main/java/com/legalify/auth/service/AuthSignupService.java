package com.legalify.auth.service;

import com.legalify.auth.dto.SignupRequestDTO;
import com.legalify.auth.model.SignupResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthSignupService {

    private final AuthManagementTokenService managementTokenService;
    private final AuthUserService userService;
    private final AuthRoleService roleService;

    public void signup(SignupRequestDTO dto) {
        // Get the management token to perform all the operations.
        String managementToken = managementTokenService.getManagementToken();

        // TODO: Do we need to evaluate if is a valid role?

        // Creates a new user
        SignupResponse signupResponse = userService.createUser(dto, managementToken);

        // Assign the given role to the new user
        // Note: The roleName will be passed from the FrontEnd,
        roleService.assignRoleToUser(signupResponse, dto.getRole(), managementToken);
    }
}
