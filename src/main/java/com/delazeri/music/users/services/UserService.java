package com.delazeri.music.users.services;

import com.delazeri.music.security.jwt.JwtUtil;
import com.delazeri.music.users.dtos.UserDTO;
import com.delazeri.music.users.entities.User;
import com.delazeri.music.users.repositories.UserRepository;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }

    public UserDTO getAuthenticatedUserData(String token) {
        String username = jwtUtil.decodedToken(token.substring(7)).getSubject();

        return ModelMapper.parseObject(repository.findByUsername(username), UserDTO.class);
    }
}

