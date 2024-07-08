package com.example.FinanceTrackerProfile.repository;

import com.example.FinanceTrackerProfile.entity.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryClass  extends JpaRepository<EntityClass,Long> {
    @Query(value = "SELECT *  FROM user_profile.profilecreation_table WHERE id = ?", nativeQuery = true)
  EntityClass  findId(String id);
    @Query(value = "SELECT *  FROM user_profile.profilecreation_table WHERE username = ?", nativeQuery = true)
    EntityClass findUserName(String userName);
    @Query(value = "SELECT *  FROM user_profile.profilecreation_table WHERE email = ?", nativeQuery = true)
    EntityClass findEmail(String email);
    @Query(value = "SELECT *  FROM user_profile.profilecreation_table WHERE phone_number = ?", nativeQuery = true)
    EntityClass findPhoneNumber(String phoneNum);
}
