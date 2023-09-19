package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.User;
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

        }

        if (userRepository.findByEmail(userInDTO.getEmail()).isPresent()) {

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
