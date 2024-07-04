package com.example.FinanceTrackerProfile.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class UpdateRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
}