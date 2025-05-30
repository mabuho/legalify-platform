package com.legalify.api.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/temp")
public class TempController {

    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hello, the Legalify API is up!");
    }
}
