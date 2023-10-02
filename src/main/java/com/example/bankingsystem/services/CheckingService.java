package com.example.bankingsystem.services;

import com.example.bankingsystem.entities.Checking;
import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.CheckingInDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CheckingService {
    public ResponseEntity<Checking> createChecking(Long userId, CheckingInDTO checkingInDTO);
    public ResponseEntity<Checking> showById(Long checkingId);
    public ResponseEntity<List<Checking>> showAll();
    public ResponseEntity<Checking> updateChecking (Long checkingId,
                                                    BigDecimal balance,
                                                    BigDecimal penaltyFee,
                                                    BigDecimal minimumBalance,
                                                    Status status,
                                                    BigDecimal monthlyMaintenanceFee);
    public ResponseEntity<?> deleteChecking (Long checkingId);
}
