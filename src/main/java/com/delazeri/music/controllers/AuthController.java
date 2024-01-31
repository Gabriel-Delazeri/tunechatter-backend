package com.delazeri.music.controllers;

import com.delazeri.music.domain.User;
import com.delazeri.music.dtos.auth.AuthenticationDto;
import com.delazeri.music.dtos.auth.LoginResponseDto;
import com.delazeri.music.dtos.auth.RegisterUserRequest;
import com.delazeri.music.services.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto authenticationData){
        System.out.println(authenticationData);
        return new ResponseEntity<>(this.service.loginUser(authenticationData), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest userData) throws Exception {
        System.out.println("CAIU AKI");
        return new ResponseEntity<>(this.service.registerUser(userData), HttpStatus.CREATED);
    }
}
