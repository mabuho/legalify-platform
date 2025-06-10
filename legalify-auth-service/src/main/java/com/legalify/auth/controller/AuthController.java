package com.legalify.auth.controller;

import com.legalify.auth.dto.SignupRequestDTO;
import com.legalify.auth.model.LoginRequest;
import com.legalify.auth.model.LoginResponse;
import com.legalify.auth.service.AuthSignupService;
import com.legalify.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthSignupService signupService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Legalify-Auth-Service!!!";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.authenticateUser(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody SignupRequestDTO dto) {
        signupService.signup(dto);
        // TODO: Retrieve the new user details.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}


