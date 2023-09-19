package com.delazeri.music.services;

import com.delazeri.music.domain.User;
import com.delazeri.music.dtos.auth.AuthenticationDto;
import com.delazeri.music.dtos.auth.LoginResponseDto;
import com.delazeri.music.dtos.auth.RegisterUserRequest;
import com.delazeri.music.infra.security.TokenService;
import com.delazeri.music.repositories.PermissionRepository;
import com.delazeri.music.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuthService {
    @Autowired
    UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public User registerUser(RegisterUserRequest userData) throws Exception {
        if (this.repository.findByUsername(userData.username()) != null) {
            System.out.println("here");
            throw new Exception("Login already in use");
        }

        User user = new User();
        user.setUsername(userData.username());
        user.setEmail(userData.email());
        user.setFirstName(userData.firstName());
        user.setLastName(userData.lastName());
        user.setPassword(new BCryptPasswordEncoder().encode(userData.password()));
        user.getPermissions().add(permissionRepository.findById(1L).orElseThrow());

        System.out.println(user);

        return this.repository.save(user);
    }

    public LoginResponseDto loginUser(AuthenticationDto authenticationData) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationData.username(),
                authenticationData.password()
        );

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        Instant expirationDate = tokenService.genExpirationDate();

        String token = tokenService.generateToken((User) auth.getPrincipal(), expirationDate);

        return new LoginResponseDto(
                token,
                LocalDateTime.ofInstant(expirationDate, ZoneId.of("America/Sao_Paulo"))
        );
    }
}
