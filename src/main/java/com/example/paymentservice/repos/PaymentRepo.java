package com.example.paymentservice.repos;

import com.example.paymentservice.entities.PaymentEntity;
import com.example.paymentservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findAllByUserAndPaymentTimeAfter(UserEntity userEntity,LocalDateTime localDateTime);
}
