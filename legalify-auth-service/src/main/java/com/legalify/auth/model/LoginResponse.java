package com.legalify.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(value = { "access_token", "token_type", "expires_in" })
public class LoginResponse {
    private String access_token;
    private String id_token;
    private String token_type;
    private int expires_in;
}
