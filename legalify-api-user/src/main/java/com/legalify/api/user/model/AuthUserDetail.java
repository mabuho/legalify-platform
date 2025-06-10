package com.legalify.api.user.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthUserDetail {
    private String name;
    private String nickname;
    private String email;
    private String userId;
    private List<String> roles;
}
