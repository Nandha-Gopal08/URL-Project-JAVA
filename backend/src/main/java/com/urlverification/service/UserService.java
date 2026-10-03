package com.urlverification.service;

import com.urlverification.model.User;
import com.urlverification.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {

        Optional<User> existingUser =
                userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "Email already registered";
        }

        userRepository.save(user);

        return "Registration successful";
    }

    public String login(String email, String password) {

        Optional<User> existingUser =
                userRepository.findByEmail(email);

        if (existingUser.isEmpty()) {
            return "User not found";
        }

        User user = existingUser.get();

        if (!user.getPassword().equals(password)) {
            return "Invalid password";
        }

        return "Login successful";
    }
}