package com.legalify.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthLoginRequestDTO extends AuthRequestDTO {

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}
