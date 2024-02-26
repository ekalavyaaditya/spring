package com.example.demo.controller;

import static org.assertj.core.api.Assertions.useDefaultDateFormatsOnly;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.example.demo.repository.DetailsDataRepository;

public class DetailsDataTest {

    @Mock
    private DetailsDataRepository detailsDataRepository;

    @InjectMocks
    private DetailsData detailsData;

    @Test
    public void testgetDetails() {
        useDefaultDateFormatsOnly();
    }
    
}