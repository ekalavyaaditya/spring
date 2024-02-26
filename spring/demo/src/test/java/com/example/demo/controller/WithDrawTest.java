package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.config.Bank;
import com.example.demo.repository.BankDataRepository;
import com.example.demo.repository.DetailsDataRepository;

@ExtendWith(MockitoExtension.class)
public class WithDrawTest {

    @Mock
    private BankDataRepository bankDataRepository;

    @Mock
    private DetailsDataRepository detailsDataRepository;

    @InjectMocks
    private WithDraw withDraw;

    @Test
    public void testPostWithDraw_ExistingUser_SufficientFunds() {
        // Mock data
        Bank bank = new Bank();
        bank.setUserId(1);
        bank.setAmount(50);

        Bank existingBank = new Bank();
        existingBank.setUserId(1);
        existingBank.setAmount(100);

        when(bankDataRepository.findById(1)).thenReturn(Optional.of(existingBank));

        String result = withDraw.postWithDraw(bank);
        verify(bankDataRepository, times(1)).findById(1);
        verify(bankDataRepository, times(1)).save(existingBank);
        verify(detailsDataRepository, times(1)).save(any());
        assertEquals("Withdraw successfull..", result);
    }
}
