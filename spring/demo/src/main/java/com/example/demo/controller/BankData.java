package com.example.demo.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.config.Bank;
import com.example.demo.config.Details;
import com.example.demo.repository.BankDataRepository;
import com.example.demo.repository.DetailsDataRepository;

import java.time.LocalDate;

@RestController

public class BankData {

    private final BankDataRepository bankDataRepository;
    private final DetailsDataRepository detailsDataRepository;
    public BankData(BankDataRepository bankDataRepository, DetailsDataRepository detailsDataRepository){
        this.bankDataRepository = bankDataRepository;
        this.detailsDataRepository = detailsDataRepository;
    }

    @PostMapping("/deposit")
    public String postBankData(@RequestBody Bank bank) {
        try {
            Bank existingBank = bankDataRepository.findById(bank.getUserId()).orElse(null);
            if (existingBank != null) {
                existingBank.setAmount(existingBank.getAmount() + bank.getAmount());
                bankDataRepository.save(existingBank);

                Details transactionDetails = new Details();
                transactionDetails.setTransactionUserId(bank.getUserId());
                transactionDetails.setTransactionDate(LocalDate.now());
                transactionDetails.setTransactionType("Credit");
                transactionDetails.setTransactionAmount(bank.getAmount());
                detailsDataRepository.save(transactionDetails);
                
                return "Bank data updated successfully.";
            } else {
                bankDataRepository.save(bank);

                Details transactionDetails = new Details();
                transactionDetails.setTransactionDate(LocalDate.now());
                transactionDetails.setTransactionType("Credit");
                transactionDetails.setTransactionAmount(bank.getAmount());
                transactionDetails.setTransactionUserId(bank.getUserId());
                detailsDataRepository.save(transactionDetails);

                return "New bank data created successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing bank data...";
        }
    }
}

