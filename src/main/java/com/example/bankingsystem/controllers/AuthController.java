package com.example.bankingsystem.controllers;

import com.example.bankingsystem.jwt.AuthResponse;
import com.example.bankingsystem.jwt.LoginRequest;
import com.example.bankingsystem.jwt.RegisterRequest;
import com.example.bankingsystem.services.AuthService;
import com.example.bankingsystem.services.dto.UserInDTO;
import com.example.bankingsystem.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserInDTO userInDTO) {
        return ResponseEntity.ok(authService.register(userInDTO));
    }
}
