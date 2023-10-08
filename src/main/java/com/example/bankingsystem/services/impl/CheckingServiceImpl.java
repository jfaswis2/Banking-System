package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.Checking;
import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.exception.BadRequestException;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.mapper.CheckingInDTOToChecking;
import com.example.bankingsystem.payload.repositories.CheckingRepository;
import com.example.bankingsystem.payload.repositories.UserRepository;
import com.example.bankingsystem.services.CheckingService;
import com.example.bankingsystem.services.dto.CheckingInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CheckingServiceImpl implements CheckingService {

    private final UserRepository userRepository;
    private final CheckingRepository checkingRepository;
    private final CheckingInDTOToChecking checkingInDTOToChecking;
    @Override
    public ResponseEntity<Checking> createChecking(Long userId, CheckingInDTO checkingInDTO) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Checking checking = checkingInDTOToChecking.map(checkingInDTO);
        checking.setUser(user);
        return ResponseEntity.ok(checkingRepository.save(checking));
    }

    @Override
    public ResponseEntity<Checking> showById(Long checkingId) {
        Checking checking = checkingRepository.findByIdAndDeletedFalse(checkingId).orElseThrow(() -> new ResourceNotFoundException("checking", "id", checkingId));
        return ResponseEntity.ok(checking);
    }

    @Override
    public ResponseEntity<List<Checking>> showAll() {
        List<Checking> checkings = checkingRepository.findByDeletedFalse().orElseThrow(() -> new ResourceNotFoundException("checkings"));
        return ResponseEntity.ok(checkings);
    }

    @Override
    public ResponseEntity<Checking> updateChecking(Long checkingId,
                                                   BigDecimal balance,
                                                   BigDecimal penaltyFee,
                                                   BigDecimal minimumBalance,
                                                   Status status,
                                                   BigDecimal monthlyMaintenanceFee) {
        Checking checking = checkingRepository.findByIdAndDeletedFalse(checkingId).orElseThrow(() -> new ResourceNotFoundException("checking", "id", checkingId));

        if (balance != null &&
                    !Objects.equals(checking.getBalance(), balance)) {
            if (balance.compareTo(checking.getMinimumBalance()) < 0) {
                throw new BadRequestException("La cuenta corriente no puede tener un saldo inferior a 250");
            }
            checking.setBalance(balance);
        }

        if (penaltyFee != null &&
                !Objects.equals(checking.getPenaltyFee(), penaltyFee)) {
            checking.setPenaltyFee(penaltyFee);
        }

        if (minimumBalance != null &&
                !Objects.equals(checking.getMinimumBalance(), minimumBalance)) {
            checking.setMinimumBalance(minimumBalance);
        }

        if (status != null &&
                !Objects.equals(checking.getStatus(), status)) {
            checking.setStatus(status);
        }

        if (monthlyMaintenanceFee != null &&
                !Objects.equals(checking.getMonthlyMaintenanceFee(), monthlyMaintenanceFee)) {
            checking.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        }


        return ResponseEntity.ok(checkingRepository.save(checking));
    }


    @Override
    public ResponseEntity<Void> deleteChecking(Long checkingId) {
        Checking checking = checkingRepository.findByIdAndDeletedFalse(checkingId)
                .orElseThrow(() -> new ResourceNotFoundException("checking", "id", checkingId));
        checking.setDeleted(true);
        checkingRepository.save(checking);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
