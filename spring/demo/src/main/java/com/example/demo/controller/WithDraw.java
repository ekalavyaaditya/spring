package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.Bank;
import com.example.demo.config.Details;
import com.example.demo.repository.BankDataRepository;
import com.example.demo.repository.DetailsDataRepository;

import java.time.LocalDate;

@RestController
public class WithDraw {

    private final BankDataRepository bankDataRepository;
    private final DetailsDataRepository detailsDataRepository;

    @Autowired
    public WithDraw(BankDataRepository bankDataRepository, DetailsDataRepository detailsDataRepository) {
        this.bankDataRepository = bankDataRepository;
        this.detailsDataRepository = detailsDataRepository;
    }

    @PostMapping("withdraw")
    public String postWithDarw(@RequestBody Bank bank){
        try {
            Bank existingBank = bankDataRepository.findById(bank.getUserId()).orElse(null);
            if (existingBank != null) {
                if (existingBank.getAmount() > bank.getAmount()) {
                    existingBank.setAmount(existingBank.getAmount() - bank.getAmount());
                    bankDataRepository.save(existingBank);

                    Details transactionDetails = new Details();
                    transactionDetails.setTransactionAmount(bank.getAmount());
                    transactionDetails.setTransactionUserId(bank.getUserId());
                    transactionDetails.setTransactionDate(LocalDate.now());
                    transactionDetails.setTransactionType("Debit");
                    detailsDataRepository.save(transactionDetails);
                    
                    return "Withdraw successfull..";
                } else {
                    return "insufficient funds..";
                }
                
            } else {
                return "there is no such userID...";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in find userId...";
        }
    }
    
}
