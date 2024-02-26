package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.config.Bank;
import com.example.demo.repository.BankDataRepository;

public class BalanceDataTest {

    @Mock
    private BankDataRepository bankDataRepository;

    @InjectMocks
    private BalanceData balanceDataController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserBalance_found() {
        int userId = 123;
        Bank bank = new Bank();
        bank.setUserId(userId);
        bank.setAmount(1700);
        when(bankDataRepository.findById(userId)).thenReturn(Optional.of(bank));
        ResponseEntity<Integer> response = balanceDataController.getUserBalance(userId);
        verify(bankDataRepository, times(1)).findById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals((Integer) 1700, response.getBody());
    }

    @Test
    public void testGetUserBalance_notFound() {
        int userId = 456;
        when(bankDataRepository.findById(userId)).thenReturn(Optional.empty());
        ResponseEntity<Integer> response = balanceDataController.getUserBalance(userId);
        verify(bankDataRepository, times(1)).findById(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
