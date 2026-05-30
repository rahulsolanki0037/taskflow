package com.project.taskflow.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.taskflow.dto.AuthResponse;
import com.project.taskflow.dto.RegisterRequest;
import com.project.taskflow.entity.User;
import com.project.taskflow.enums.Role;
import com.project.taskflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder().accessToken(jwtService.generateToken(user.getEmail())).build();
    }

    public AuthResponse login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return AuthResponse.builder().errorMessage("User not found with email: " + email).build();
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return AuthResponse.builder().errorMessage("Invalid credentials").build();
        }

        return AuthResponse.builder().accessToken(jwtService.generateToken(user.getEmail())).build();
    }
}
