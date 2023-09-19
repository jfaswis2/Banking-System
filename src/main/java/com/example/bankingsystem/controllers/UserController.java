package com.example.bankingsystem.controllers;

import com.example.bankingsystem.services.dto.UserInDTO;
import com.example.bankingsystem.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserInDTO userInDTO) {
        return userService.createUser(userInDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        return userService.showById(id);
    }

    @GetMapping("users")
    public ResponseEntity<?> showAll() {
        return userService.showALl();
    }
}
