package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.exception.ResourceConflictException;
import com.example.bankingsystem.jwt.AuthResponse;
import com.example.bankingsystem.jwt.JwtService;
import com.example.bankingsystem.jwt.LoginRequest;
import com.example.bankingsystem.jwt.RegisterRequest;
import com.example.bankingsystem.mapper.UserInDTOToUser;
import com.example.bankingsystem.repositories.UserRepository;
import com.example.bankingsystem.services.AuthService;
import com.example.bankingsystem.services.UserService;
import com.example.bankingsystem.services.dto.UserInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public AuthResponse register(UserInDTO userInDTO) {
        if (userRepository.findByDni(userInDTO.getDni()).isPresent()) {
            throw new ResourceConflictException(String.format("El DNI: %s ya está registrado en el sistema", userInDTO.getDni()));
        }

        if (userRepository.findByEmail(userInDTO.getEmail()).isPresent()) {
            throw new ResourceConflictException(String.format("El email: %s ya está registrado en el sistema", userInDTO.getEmail()));
        }

        User user = userRepository.save(userInDTOToUser.map(userInDTO));

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
