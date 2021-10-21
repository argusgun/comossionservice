package com.example.paymentservice.repos;

import com.example.paymentservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findUserEntityByPhoneNumber(String phoneNumber);
}
