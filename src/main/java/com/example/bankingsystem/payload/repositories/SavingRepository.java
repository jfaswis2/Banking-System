package com.example.bankingsystem.payload.repositories;

import com.example.bankingsystem.entities.Saving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavingRepository extends JpaRepository<Saving, Long> {
    Optional<Saving> findByIdAndDeletedFalse(Long id);
    Optional<List<Saving>> findByDeletedFalse();
}
