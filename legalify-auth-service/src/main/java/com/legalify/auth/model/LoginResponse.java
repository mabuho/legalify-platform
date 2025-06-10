package com.legalify.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "token_type" })
public class LoginResponse {

    private String access_token;
    private String id_token;
    private String token_type;
    private int expires_in;

}
