package com.example.FinanceTrackerProfile.repository;

import com.example.FinanceTrackerProfile.entity.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryClass  extends JpaRepository<EntityClass,Long> {
    EntityClass getById(long id);
    EntityClass findByUserName(String userName);
    EntityClass findByEmail(String email);
    EntityClass findByPhoneNumber(String phoneNum);
}
