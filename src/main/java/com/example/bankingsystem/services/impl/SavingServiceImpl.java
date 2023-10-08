package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.Saving;
import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.exception.BadRequestException;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.mapper.SavingInDTOToSaving;
import com.example.bankingsystem.payload.repositories.SavingRepository;
import com.example.bankingsystem.payload.repositories.UserRepository;
import com.example.bankingsystem.services.SavingService;
import com.example.bankingsystem.services.dto.SavingInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SavingServiceImpl implements SavingService {

    private final UserRepository userRepository;
    private final SavingRepository savingRepository;
    private final SavingInDTOToSaving savingInDTOToSaving;
    @Override
    public ResponseEntity<Saving> createSaving(Long userId, SavingInDTO savingInDTO) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Saving saving = savingInDTOToSaving.map(savingInDTO);

        if (saving.getInterestRate() == null) {
            saving.setInterestRate(BigDecimal.valueOf(0.0025));
        }

        saving.setUser(user);
        return ResponseEntity.ok(savingRepository.save(saving));
    }

    @Override
    public ResponseEntity<Saving> showById(Long savingId) {
        Saving saving = savingRepository.findByIdAndDeletedFalse(savingId).orElseThrow(() -> new ResourceNotFoundException("saving", "id", savingId));
        return ResponseEntity.ok(saving);
    }

    @Override
    public ResponseEntity<List<Saving>> showAll() {
        List<Saving> savings = savingRepository.findByDeletedFalse().orElseThrow(() -> new ResourceNotFoundException("savings"));
        return ResponseEntity.ok(savings);
    }

    @Override
    public ResponseEntity<Saving> updateSaving(Long savingId,
                                               BigDecimal balance,
                                               BigDecimal penaltyFee,
                                               BigDecimal minimumBalance,
                                               Status status,
                                               BigDecimal interestRate) {

        Saving saving = savingRepository.findByIdAndDeletedFalse(savingId).orElseThrow(() -> new ResourceNotFoundException("saving", "id", savingId));

        if (balance != null &&
                !Objects.equals(saving.getBalance(), balance)) {
            if (balance.compareTo(saving.getMinimumBalance()) < 0) {
                throw new BadRequestException("La cuenta de ahorro no puede tener un saldo inferior a " + saving.getMinimumBalance().longValue());
            }
            saving.setBalance(balance);
        }

        if (penaltyFee != null &&
                !Objects.equals(saving.getPenaltyFee(), penaltyFee)) {
            saving.setPenaltyFee(penaltyFee);
        }

        if (minimumBalance != null &&
                !Objects.equals(saving.getMinimumBalance(), minimumBalance)) {
            saving.setMinimumBalance(minimumBalance);
        }

        if (status != null &&
                !Objects.equals(saving.getStatus(), status)) {
            saving.setStatus(status);
        }

        if (interestRate != null &&
                !Objects.equals(saving.getInterestRate(), interestRate)) {
            saving.setInterestRate(interestRate);
        }

        return ResponseEntity.ok(savingRepository.save(saving));
    }

    @Override
    public ResponseEntity<?> deleteSaving(Long savingId) {
        Saving saving = savingRepository.findByIdAndDeletedFalse(savingId)
                .orElseThrow(() -> new ResourceNotFoundException("saving", "id", savingId));
        saving.setDeleted(true);
        savingRepository.save(saving);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
