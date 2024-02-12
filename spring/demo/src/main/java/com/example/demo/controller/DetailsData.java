package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Details;
import com.example.demo.repository.DetailsDataRepository;

@RestController
public class DetailsData {
    private final DetailsDataRepository detailsDataRepository;
    public DetailsData(DetailsDataRepository detailsDataRepository){
        this.detailsDataRepository = detailsDataRepository;
    }

    @GetMapping("/details")
    public ResponseEntity<?> getDetails() {
        try {
        Iterable<Details> detailsIterable = detailsDataRepository.findAll();
        return new ResponseEntity<>(detailsIterable, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error fetching details.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}