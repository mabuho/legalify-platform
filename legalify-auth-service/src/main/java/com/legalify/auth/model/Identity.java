package com.legalify.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Identity {

    public String connection;
    @JsonProperty("user_id")
    public String userId;
    public String provider;
    public boolean isSocial;

}
