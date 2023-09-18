package com.delazeri.music.services;

import com.delazeri.music.domain.User;
import com.delazeri.music.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUserByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
