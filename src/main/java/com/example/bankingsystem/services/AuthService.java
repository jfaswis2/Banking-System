package com.example.bankingsystem.services;

import com.example.bankingsystem.jwt.AuthResponse;
import com.example.bankingsystem.jwt.LoginRequest;
import com.example.bankingsystem.jwt.RegisterRequest;
import com.example.bankingsystem.services.dto.UserInDTO;

public interface AuthService {
    public AuthResponse login(LoginRequest request);
    public AuthResponse register(UserInDTO userInDTO);
}
