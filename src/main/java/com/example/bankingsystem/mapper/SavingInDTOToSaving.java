package com.example.bankingsystem.mapper;

import com.example.bankingsystem.entities.Saving;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.SavingInDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SavingInDTOToSaving implements IMapper<SavingInDTO, Saving> {
    @Override
    public Saving map(SavingInDTO in) {
        Saving saving = new Saving();
        saving.setInterestRate(in.getInterestRate());
        saving.setBalance(in.getBalance());
        saving.setMinimumBalance(BigDecimal.valueOf(1000));
        saving.setPenaltyFee(BigDecimal.valueOf(40));
        saving.setDeleted(false);
        saving.setCreationDate(LocalDate.now());
        saving.setStatus(Status.ACTIVE);

        return saving;
    }
}
