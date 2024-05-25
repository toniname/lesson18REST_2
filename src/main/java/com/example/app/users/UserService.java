package com.example.app.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository repository;



    public User findByUsername(String username) {
        Optional<User> user = repository.findById(Long.valueOf(username));

        return user.orElse(null);
    }
    public void saveUser(User user) {
        repository.save(user);
    }
}