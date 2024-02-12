package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.config.Details;

public interface DetailsDataRepository extends JpaRepository <Details, Integer>{
    
}
