package com.example.bankingsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "dni", unique = true)
    private String dni;
    @Column(name = "password")
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Checking> checkings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Saving> savings;
}
