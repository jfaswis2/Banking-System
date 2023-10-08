package com.example.bankingsystem.payload.repositories;

import com.example.bankingsystem.entities.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheckingRepository extends JpaRepository<Checking, Long> {
    Optional<Checking> findByIdAndDeletedFalse(Long id);
    Optional<List<Checking>> findByDeletedFalse();
}
