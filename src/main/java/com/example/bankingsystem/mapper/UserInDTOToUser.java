package com.example.bankingsystem.mapper;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.services.dto.UserInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserInDTOToUser implements IMapper<UserInDTO, User>{

    @Override
    public User map(UserInDTO in) {
        User user = User.builder()
                .name(in.getName())
                .lastName(in.getLastName())
                .dni(in.getDni())
                .email(in.getEmail())
                .dateOfBirth(in.getDateOfBirth())
                .password(in.getPassword())
                .deleted(false)
                .creationDate(LocalDate.now()).build();

        return user;
    }
}
