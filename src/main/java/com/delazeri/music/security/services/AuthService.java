package com.delazeri.music.security.services;

import com.delazeri.music.security.dtos.AccountCredentialsDTO;
import com.delazeri.music.security.dtos.SignUpCredentialsDTO;
import com.delazeri.music.security.dtos.TokenDTO;
import com.delazeri.music.users.dtos.User;
import com.delazeri.music.security.jwt.JwtTokenProvider;
import com.delazeri.music.users.dtos.UserRepository;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data) {
        try {
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            User user = repository.findByUsername(username);

            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        User user = repository.findByUsername(username);

        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

    public boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }

    public boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return  refreshToken == null || refreshToken.isBlank()
                || username == null || username.isBlank();
    }

    public TokenDTO signup(SignUpCredentialsDTO signUpCredentialsDTO) {
            User user = ModelMapper.parseObject(signUpCredentialsDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setEnabled(true);
            user.setCredentialsNonExpired(true);
            user.setPermissions(new ArrayList<>());

            repository.save(user);

            return tokenProvider.createAccessToken(user.getUsername(), user.getRoles());
    }
}
