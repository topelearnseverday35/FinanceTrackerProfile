package com.example.FinanceTrackerProfile.utils;

import com.example.FinanceTrackerProfile.entity.EntityClass;
import com.example.FinanceTrackerProfile.repository.RepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.regex.Pattern;


@Component
public  class Helper {

    @Autowired
    RepositoryClass repositoryClass;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

//Name input Validation Method
    public  boolean name_validation(String firstname) {
        if (firstname != null && firstname.length() > 2 && firstname.length() < 25) {
            return true;
        }
        return false;
    }

    //Email input Validation Method



    //password input Validation Method

    public  boolean password_validation(String password) {
        if (password != null && password.length() > 10 && password.length() < 30) {
            return true;
        }
        return false;
    }

    //Password input Validation Method

    public boolean phone_validation(String phone) {
        int tel;
        try {
            //tel = Integer.parseInt(phone);
            if (phone.length() == 11) {
                return true;
            }
        }catch (Exception e) {
            return false;
        }
return false;
    }

    //Gender input Validation Method

    public  boolean gender_validation(String gender) {
        if(gender != null && !gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")){
            return false;
        }
        return true;
    }
    public  boolean username_validation(String username) {
        if( username!=null && username.length() >7 && username.length() < 20) {
            EntityClass entityClass;
            try {
                entityClass = repositoryClass.findUserName(username);
            }catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }




}
