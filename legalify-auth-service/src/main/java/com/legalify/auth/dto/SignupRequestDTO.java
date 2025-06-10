package com.legalify.auth.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String email;
    private String password;
    private String role;
}
