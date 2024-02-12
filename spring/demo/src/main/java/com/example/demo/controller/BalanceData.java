package com.example.demo.controller;

import com.example.demo.repository.BankDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Bank;

@RestController
public class BalanceData {
    private final BankDataRepository bankDataRepository;
    
    public BalanceData(BankDataRepository bankDataRepository) {
        this.bankDataRepository = bankDataRepository;
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Integer> getUserBalance(@PathVariable int userId) {
        Bank bank = bankDataRepository.findById(userId).orElse(null);
        if (bank != null) {
            int balance = bank.getAmount();
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
