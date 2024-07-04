package com.example.FinanceTrackerProfile.controller;

import com.example.FinanceTrackerProfile.dto.FinanceTrackerRequest;
import com.example.FinanceTrackerProfile.dto.UpdateRequest;
import com.example.FinanceTrackerProfile.service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/FinanceTracker/Profile")
public class ControllerClass {

    @Autowired
    ServiceClass serviceClass;

    @PostMapping("/Creation")
    public Object userProfile(@RequestBody FinanceTrackerRequest financeTrackerRequest) {
    return serviceClass.createProfile(financeTrackerRequest);
    }

    @GetMapping("/retrieve-by")
    public Object retrievalProfile(@RequestParam String userName,
                                   @RequestParam String email,
                                   @RequestParam String phoneNumber) {
        return serviceClass.retrieveProfile( userName, email,phoneNumber);
    }
    @GetMapping("/retrieve-by-email")
    public Object retrieveSpecifically(@RequestParam  String email){
        return serviceClass.retrieveProfileByEmail(email);

    }
    @GetMapping("/retrieve-by-phoneNumber")
    public Object retrieveSpecifically2(@RequestParam  String phoneNumber){
        return serviceClass.retrieveProfileByphoneNumber(phoneNumber);

    }

    @GetMapping("/retrieve-by-userName")
    public Object retrieveSpecifically3(@RequestParam  String userName){
        return serviceClass.retrieveProfileByuserName(userName);

    }
    @PutMapping("/update-info")
    public Object updateProfile1(@RequestBody UpdateRequest updateRequest, @RequestParam Long id) {
        return serviceClass.updateProfile(updateRequest, id);

    }
    @DeleteMapping("/delete-info")

    public Object deleteProfile1(@RequestParam Long id) {
        return serviceClass.deleteProfile(id);
    }
}
