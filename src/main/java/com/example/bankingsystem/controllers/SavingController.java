package com.example.bankingsystem.controllers;

import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.CheckingInDTO;
import com.example.bankingsystem.services.dto.SavingInDTO;
import com.example.bankingsystem.services.impl.SavingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class SavingController {

    private final SavingServiceImpl savingService;

    @PostMapping("/saving/{userId}")
    public ResponseEntity<?> createSaving(@RequestBody @Valid SavingInDTO savingInDTO, @PathVariable("userId") Long userId) {
        return savingService.createSaving(userId, savingInDTO);
    }

    @PutMapping("/saving/{savingId}")
    public ResponseEntity<?> updateSaving(@PathVariable("savingId") Long savingId,
                                          @RequestParam(required = false) BigDecimal balance,
                                          @RequestParam(required = false) BigDecimal penaltyFee,
                                          @RequestParam(required = false) BigDecimal minimumBalance,
                                          @RequestParam(required = false) Status status,
                                          @RequestParam(required = false) BigDecimal interestRate) {

        return savingService.updateSaving(savingId, balance, penaltyFee, minimumBalance, status, interestRate);
    }

    @GetMapping("/saving/{savingId}")
    public ResponseEntity<?> showById(@PathVariable Long savingId) {
        return savingService.showById(savingId);
    }

    @GetMapping("/savings")
    public ResponseEntity<?> showAll() {
        return savingService.showAll();
    }

    @DeleteMapping("/saving/{savingId}")
    public ResponseEntity<?> deleteSaving(@PathVariable Long savingId) {
        return savingService.deleteSaving(savingId);
    }
}
