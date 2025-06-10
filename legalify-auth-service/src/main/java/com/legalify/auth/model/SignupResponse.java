package com.legalify.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SignupResponse {

    @JsonProperty("created_at")
    private String createdAt;
    private String email;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    private List<Identity> identities;
    private String name; // Users can update this field
    private String nickname;
    private String picture;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("user_id")
    private String userId;

}
