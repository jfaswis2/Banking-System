package com.example.bankingsystem.mapper;

public interface IMapper <I, O> {
    O map(I in);
}
