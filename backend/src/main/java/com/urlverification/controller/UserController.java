// Step 8 — Add Profile API
// File:
// backend/src/main/java/com/urlverification/controller/UserController.java

package com.urlverification.controller;

import com.urlverification.model.User;
import com.urlverification.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // REGISTER

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already registered";
        }

        userRepository.save(user);

        return "Registration successful";
    }


    // LOGIN

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password) {

        User user =
                userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(password)) {
            return "Invalid password";
        }

        return "Login successful";
    }


    // GET PROFILE

    @GetMapping("/profile")
    public User getProfile(
            @RequestParam String email) {

        return userRepository
                .findByEmail(email)
                .orElse(null);
    }


    // UPDATE PROFILE

    @PutMapping("/profile")
    public String updateProfile(
            @RequestParam String email,
            @RequestParam String name) {

        User user =
                userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "User not found";
        }

        user.setName(name);

        userRepository.save(user);

        return "Profile updated successfully";
    }
}