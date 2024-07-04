package com.example.FinanceTrackerProfile.dto;

import lombok.Data;


@Data

public class FinanceTrackerRequest {
    private String firstname;
    private String lastname;
    private String password;
    private String username;
    private String gender;
    private String phoneNumber;
    private String email;
}
