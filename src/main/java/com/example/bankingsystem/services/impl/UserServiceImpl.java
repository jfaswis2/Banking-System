package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.exception.ResourceConflictException;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.mapper.UserInDTOToUser;
import com.example.bankingsystem.repositories.UserRepository;
import com.example.bankingsystem.services.UserService;
import com.example.bankingsystem.services.dto.UserInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;

    @Override
    public ResponseEntity<User> createUser(UserInDTO userInDTO) {

        if (userRepository.findByDni(userInDTO.getDni()).isPresent()) {
            throw new ResourceConflictException(String.format("El DNI: %s ya está registrado en el sistema", userInDTO.getDni()));
        }

        if (userRepository.findByEmail(userInDTO.getEmail()).isPresent()) {
            throw new ResourceConflictException(String.format("El email: %s ya está registrado en el sistema", userInDTO.getEmail()));
        }
        return ResponseEntity.ok(userRepository.save(userInDTOToUser.map(userInDTO)));
    }

    @Override
    public ResponseEntity<User> showById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> showALl() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("users");
        }
        return ResponseEntity.ok(users);
    }
}
