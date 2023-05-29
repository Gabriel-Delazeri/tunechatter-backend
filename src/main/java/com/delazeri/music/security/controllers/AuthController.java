package com.delazeri.music.security.controllers;

import com.delazeri.music.security.dtos.AccountCredentialsDTO;
import com.delazeri.music.security.dtos.SignUpCredentialsDTO;
import com.delazeri.music.security.dtos.TokenDTO;
import com.delazeri.music.security.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<TokenDTO> signup(@RequestBody @Valid SignUpCredentialsDTO signUpCredentialsDTO) {
        return ResponseEntity.ok().body(authService.signup(signUpCredentialsDTO));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
        if (authService.checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        ResponseEntity token = authService.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity signin(
            @PathVariable String username,
            @RequestHeader("Authorization") String refreshToken
    ) {
        if (authService.checkIfParamsIsNotNull(username,refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        ResponseEntity token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }
}
