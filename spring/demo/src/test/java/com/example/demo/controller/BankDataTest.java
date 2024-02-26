package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.Bank;
import com.example.demo.config.Details;
import com.example.demo.repository.BankDataRepository;
import com.example.demo.repository.DetailsDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankDataTest {

    @Autowired
    private BankData bankData;

    @Mock
    private BankDataRepository bankDataRepository;

    @Mock
    private DetailsDataRepository detailsDataRepository;

    @Test
    public void postBankData() {
        BankData bankData = new BankData(bankDataRepository, detailsDataRepository);
        
        Bank bank = new Bank();
        int userId = 123;
        bank.setUserId(userId);
        bank.setAmount(100);

        Bank existingBank = new Bank();
        existingBank.setUserId(userId);

        when(bankDataRepository.findById(userId)).thenReturn(Optional.of(existingBank));
        when(bankDataRepository.save(any(Bank.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(detailsDataRepository.save(any(Details.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        String result = bankData.postBankData(bank);
        assertEquals("Bank data updated successfully.", result);
    }
}
