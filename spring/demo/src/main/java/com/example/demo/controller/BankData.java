package com.example.demo.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import java.util.Map;
import com.example.demo.config.Bank;
import com.example.demo.config.Details;
import com.example.demo.repository.BankDataRepository;
import com.example.demo.repository.DetailsDataRepository;

import java.time.LocalDate;

@RestController
// @RequestMapping("/api")
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

    
    // @PostMapping("/withdraw")
    // public ResponseEntity<String> withdraw(@RequestParam int userId, @RequestBody Map<String, Integer> requestBody) {
    //     int amount = requestBody.get("amount");
        
    //     Bank bank = bankDataRepository.findById(userId).orElse(null);
    //     if (bank == null) {
    //         return ResponseEntity.badRequest().body("Bank account not found for user ID: " + userId);
    //     }
        
    //     if (bank.getAmount() < amount) {
    //         return ResponseEntity.badRequest().body("Insufficient funds in the bank account.");
    //     }
        
    //     bank.setAmount(bank.getAmount() - amount);
    //     bankDataRepository.save(bank);
        
    //     Details details = new Details();
    //     details.setUserId(userId);
    //     details.setTransactionDate(LocalDate.now());
    //     details.setTransactionType("Withdraw");
    //     details.setTransactionAmount(amount);
    //     detailsDataRepository.save(details);
        
    //     return ResponseEntity.ok("Withdrawal successful.");
    // }
    // @GetMapping("/balance/{userId}")
    // public ResponseEntity<Integer> getUserBalance(@PathVariable int userId) {
    //     Bank bank = bankDataRepository.findById(userId).orElse(null);
    //     if (bank != null) {
    //         int balance = bank.getAmount();
    //         return ResponseEntity.ok(balance);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}

