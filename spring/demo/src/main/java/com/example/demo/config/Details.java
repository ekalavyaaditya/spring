package com.example.demo.config;

import java.time.LocalDate;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DETAILS")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId")
    private int transactionId;
    
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    // private Bank bank;
    @Column(name = "UserId")
    private int userId;

    @Column(name = "TransactionDate")
    private LocalDate transactionDate;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "TransactionAmount")
    private int transactionAmount;
    // rdbms is not able to add so make a method and adding it     
    public void setTransactionUserId(int userId) {
        this.userId = userId;
    }
}
