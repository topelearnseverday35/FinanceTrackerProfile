package com.example.FinanceTrackerProfile.service;

import com.example.FinanceTrackerProfile.dto.FinanceTrackerRequest;
import com.example.FinanceTrackerProfile.dto.FinanceTrackerResponse;
import com.example.FinanceTrackerProfile.dto.UpdateRequest;
import com.example.FinanceTrackerProfile.entity.EntityClass;
import com.example.FinanceTrackerProfile.repository.RepositoryClass;
import com.example.FinanceTrackerProfile.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ServiceClass {
    @Autowired
    RepositoryClass repositoryClass;

    @Autowired
    Helper helper;


    public Object createProfile(FinanceTrackerRequest financeTrackerRequest) {


        boolean fname = helper.name_validation(financeTrackerRequest.getFirstname());
        boolean lname = helper.name_validation(financeTrackerRequest.getLastname());
        boolean password = helper.password_validation(financeTrackerRequest.getPassword());
        boolean gender = helper.gender_validation(financeTrackerRequest.getGender());
        boolean phoneNumber = helper.phone_validation(financeTrackerRequest.getPhoneNumber());
        boolean userName = helper.username_validation(financeTrackerRequest.getUsername());
//      boolean email2 = helper.email_DuplicateValidation(financeTrackerRequest.getEmail());

        if (!fname) {
            return ("Invalid data entry. Please check your firstname input!");
        }
        if (!lname) {
            return ("Invalid data entry. Please check your lastname input!");
        }
        EntityClass byEmail;
        try {
            byEmail = repositoryClass.findEmail(financeTrackerRequest.getEmail());
            log.info("By Email entity{}", byEmail);
        } catch (Exception e) {
            log.info("Error trying to get by Email. ERR: {}", e.getMessage());
            return ("Server Error! ,Try again later");
        }

        if (byEmail != null) {
            return ("Email already exists! Please choose another Email.");
        }
        if (!password) {
            return ("Invalid data entry. Please check your password input!");
        }
        if (!gender) {
            return (" Please check your gender input!");
        }
        if (!phoneNumber) {
            return ("Please check that your phone number is exactly 11 digits!");
        }
        if (!userName) {
            return ("Please check your username input!");
        }
// For Duplicate phoneNumber
        EntityClass byPhoneNum;
        try {
            byPhoneNum = repositoryClass.findPhoneNumber(financeTrackerRequest.getPhoneNumber());
        } catch (Exception e) {
            log.info("Error trying to get by phone number. ERR: " + e.getMessage());
            return ("Server Error! ,Try again later");
        }

        if (byPhoneNum != null) {
            return ("Phone Number already exists! Please choose another phone number.");
        }

//For Duplicate UserName
        EntityClass username;
        try {
            username = repositoryClass.findUserName(financeTrackerRequest.getUsername());
        } catch (Exception e) {
            log.info("Error trying to get by username. ERR: " + e.getMessage());
            return ("Server Error! ,Try again later");
        }

        if (username != null) {
            return ("UserName already exists! Please choose another UserName.");
        }




// Profile Creation

        EntityClass entityClass = new EntityClass();
        entityClass.setFirstname(financeTrackerRequest.getFirstname());
        entityClass.setLastname(financeTrackerRequest.getLastname());
        entityClass.setEmail(financeTrackerRequest.getEmail());
        entityClass.setGender(financeTrackerRequest.getGender());
        entityClass.setUserName(financeTrackerRequest.getUsername());
        entityClass.setPassword(financeTrackerRequest.getPassword());
        entityClass.setPhoneNumber(financeTrackerRequest.getPhoneNumber());
        repositoryClass.save(entityClass);

        FinanceTrackerResponse financeTrackerResponse = new FinanceTrackerResponse();
        financeTrackerResponse.setDetails1("Your Username is " + entityClass.getUserName());
        financeTrackerResponse.setDetails2("Your password is " + entityClass.getPassword());
        financeTrackerResponse.setStatus("Account Created Successfully");
        return financeTrackerResponse;

    }

    public Object retrieveProfile( String phoneNumber) {
        List<EntityClass> entityProfile;
        //For if phoneNumber field is empty in database
        if (phoneNumber != null) {
            EntityClass retrieveByphoneNumber = null;
            try {
                retrieveByphoneNumber = repositoryClass.findPhoneNumber(phoneNumber);
            } catch (Exception e) {
                log.info("Can Not Retrieve Profile. ERR: " + e.getMessage());
                return "Error retrieving Profile.";
//                return Objects.requireNonNullElse(retrieveByphoneNumber, "User with phoneNumber does not exist");

            }


        }

        try {
            entityProfile = repositoryClass.findAll();
        } catch (Exception e) {
            System.out.println("Error retrieving Profile. ERR: " + e.getMessage());
            return "Error retrieving Profile.";
        }
        return entityProfile;
    }

    //Methods to retrieve info by provided parameters
    public Object retrieveProfileByEmail(String email) {
        Object response;
        try {
            response = repositoryClass.findEmail(email);
            if (response == null) {
                response = "Profile with this email does not exist";
            }
        } catch (Exception e) {
            log.info("Can not retrieve Profile. ERR: " + e.getMessage());
            response = "Error retrieving Profile.";
        }
        return response;

    }

    public Object retrieveProfileByphoneNumber(String phoneNumber) {
        Object response;
        try {
            response = repositoryClass.findPhoneNumber(phoneNumber);
            if (response == null) {
                response = "Profile with this phoneNumber does not exist";
            }
        } catch (Exception e) {
            log.info("Error retrieving Profile. ERR: {}", e.getMessage());
            response = "Error retrieving Profile.";
        }
        return response;


    }

    public Object retrieveProfileByuserName(String userName) {
        Object response;
        try {
            response = repositoryClass.findUserName(userName);
            if (response == null) {
                response = "Profile with this UserName does not exist";
            }
        } catch (Exception e) {
            System.out.println("Error retrieving Profile. ERR: " + e.getMessage());
            response = "Error retrieving Profile.";
        }
        return response;
    }
// Update Method
    public Object updateProfile(UpdateRequest updateRequest, Long id) {
        Optional<EntityClass> userProfile2 = repositoryClass.findById(id);
        if (updateRequest == null){
            return  "Input Field is empty";
        

        }
        if (userProfile2.isEmpty()) {
            log.info("Error updating Profile. ID not found");
            return ("Error updating Profile. ID not found");
        }
        boolean nameisValid = helper.name_validation(updateRequest.getFirstname());
        boolean lastnameisValid = helper.name_validation(updateRequest.getLastname());
        boolean phoneNumberisValid = helper.phone_validation(updateRequest.getPhoneNumber());
        if (nameisValid) {
            userProfile2.get().setFirstname(updateRequest.getFirstname());
            //OR
            // userProfile2.ifPresent(entityClass -> entityClass.setFirstname(updateRequest.getFirstname()));
        }




        if (lastnameisValid) {
            userProfile2.get().setLastname(updateRequest.getLastname());
        }

        if (phoneNumberisValid) {
           EntityClass userExist = repositoryClass.findPhoneNumber(updateRequest.getPhoneNumber());
            if(userExist != null) {
                return "User with this Phone Number already exists";
            }
            userProfile2.get().setPhoneNumber(updateRequest.getPhoneNumber());
        }

       return repositoryClass.save(userProfile2.get());


    }

 //Delete Method
    public Object deleteProfile(Long id) {
        Optional<EntityClass> userProfile2 = repositoryClass.findById(id);
        if (userProfile2.isEmpty()) {
            log.info("Error deleting Profile. ID not found");
            return "Error deleting Profile. ID not found";
        }
        repositoryClass.delete(userProfile2.get());
        return  "Profile Deleted Successfully";


    }

}

