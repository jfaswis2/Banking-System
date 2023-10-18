package com.example.bankingsystem.mapper;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.services.dto.UserInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserInDTOToUser implements IMapper<UserInDTO, User>{

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserInDTO in) {
        User user = User.builder()
                .name(in.getName())
                .lastName(in.getLastName())
                .dni(in.getDni())
                .email(in.getEmail())
                .dateOfBirth(in.getDateOfBirth())
                .password(passwordEncoder.encode(in.getPassword()))
                .deleted(false)
                .creationDate(LocalDate.now()).build();

        return user;
    }
}
