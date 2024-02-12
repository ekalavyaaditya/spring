package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.config.Bank;

public interface BankDataRepository extends JpaRepository<Bank, Integer>{

}