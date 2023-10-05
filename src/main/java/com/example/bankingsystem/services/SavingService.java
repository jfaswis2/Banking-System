package com.example.bankingsystem.services;

import com.example.bankingsystem.entities.Saving;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.SavingInDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface SavingService {

    public ResponseEntity<Saving> createSaving(Long userId, SavingInDTO savingInDTO);
    public ResponseEntity<Saving> showById(Long savingId);
    public ResponseEntity<List<Saving>> showAll();
    public ResponseEntity<Saving> updateSaving(Long savingId,
                                               BigDecimal balance,
                                               BigDecimal penaltyFee,
                                               BigDecimal minimumBalance,
                                               Status status,
                                               BigDecimal interestRate);
    public ResponseEntity<?> deleteSaving(Long savingId);
}
