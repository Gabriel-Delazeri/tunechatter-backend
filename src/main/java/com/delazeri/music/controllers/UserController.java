package com.delazeri.music.controllers;

import com.delazeri.music.dtos.users.UserDTO;
import com.delazeri.music.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/data")
    public ResponseEntity<UserDTO> getAuthenticatedUserData(@RequestHeader("Authorization") String token) {
        // todo refactor
        return ResponseEntity.ok().body(new UserDTO());
    }
}
