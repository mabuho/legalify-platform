package com.legalify.api.user.controller;

import com.legalify.api.user.model.AuthUserDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TempController {

    private static final String CLAIMS_NAMESPACE = "https://korvex.io/claims/";

    @GetMapping("public/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hello, the Legalify API is up!");
    }

    @GetMapping("private/hello")
    public ResponseEntity<Object> privateHello(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(AuthUserDetail.builder()
                .name(jwt.getClaimAsString(CLAIMS_NAMESPACE + "name"))
                .nickname(jwt.getClaimAsString(CLAIMS_NAMESPACE + "nickname"))
                .email(jwt.getClaimAsString(CLAIMS_NAMESPACE + "email"))
                .userId(jwt.getSubject())
                .roles(jwt.getClaimAsStringList(CLAIMS_NAMESPACE + "roles"))
                .build()
        );
    }

    @PreAuthorize("hasAuthority('SCOPE_read:current_user')")
    @GetMapping("private/profile")
    public ResponseEntity<Object> userProfile() {
        return ResponseEntity.ok("Authenticated!!!!");
    }
}
