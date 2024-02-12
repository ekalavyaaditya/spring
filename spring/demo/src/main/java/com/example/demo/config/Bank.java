package com.example.demo.config;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BANK")
public class Bank {
    @Id
    @Column(name = "UserId")
    private int userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "AccNO")
    private int accNO;

    @Column(name = "Amount")
    private int amount;
    
}