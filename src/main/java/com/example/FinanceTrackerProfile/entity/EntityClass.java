package com.example.FinanceTrackerProfile.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "profilecreation_table")
public class EntityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstname;
    private String lastname;
    private String password;
//    @Column(unique = true)
    private String userName;
    private String gender;
//    @Column(unique = true)
    private String phoneNumber;
//    @Column(unique = true)
    private String email;

}
