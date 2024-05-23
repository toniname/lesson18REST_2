package com.example.app.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByUsername(String username) {
        Optional<User> user = repository.findById(Long.valueOf(username));

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
}