package com.example.bankingsystem.controllers;

import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.CheckingInDTO;
import com.example.bankingsystem.services.impl.CheckingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class CheckingController {

    private final CheckingServiceImpl checkingService;

    @PostMapping("/checking/{userId}")
    public ResponseEntity<?> createChecking(@RequestBody @Valid CheckingInDTO checkingInDTO, @PathVariable("userId") Long userId) {
        return checkingService.createChecking(userId, checkingInDTO);
    }

    @PutMapping("/checking/{checkingId}")
    public ResponseEntity<?> updateChecking(
                                            @PathVariable("checkingId") Long checkingId,
                                            @RequestParam(required = false) BigDecimal balance,
                                            @RequestParam(required = false) BigDecimal penaltyFee,
                                            @RequestParam(required = false) BigDecimal minimumBalance,
                                            @RequestParam(required = false) Status status,
                                            @RequestParam(required = false) BigDecimal monthlyMaintenanceFee) {

        return checkingService.updateChecking(checkingId, balance, penaltyFee, minimumBalance, status, monthlyMaintenanceFee);
    }

    @GetMapping("/checking/{checkingId}")
    public ResponseEntity<?> showById(@PathVariable Long checkingId) {
        return checkingService.showById(checkingId);
    }

    @GetMapping("/checkings")
    public ResponseEntity<?> showAll() {
        return checkingService.showAll();
    }

    @DeleteMapping("/checking/{checkingId}")
    public ResponseEntity<?> deleteChecking(@PathVariable Long checkingId) {
        return checkingService.deleteChecking(checkingId);
    }
}
