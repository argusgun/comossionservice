package com.example.paymentservice.services;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.entities.PaymentEntity;
import com.example.paymentservice.entities.UserEntity;
import com.example.paymentservice.repos.PaymentRepo;
import com.example.paymentservice.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComissionService {
    private final UserRepo userRepo;
    private final PaymentRepo paymentRepo;

    public double getComissionPercent(PaymentDto paymentDto) {
        int comission = 1;
        UserEntity userEntity;
        if (paymentDto.getUserId() != null) {
            userEntity = userRepo.getById(paymentDto.getUserId());
        } else {
            userEntity = userRepo.findUserEntityByPhoneNumber(paymentDto.getPhoneNumber());
        }
        List<PaymentEntity> paymentEntities = paymentRepo.findAllByUserAndPaymentTimeAfter(userEntity, LocalDateTime.of(paymentDto.getLocalDateTime().getYear(), paymentDto.getLocalDateTime().getMonth(), 1, 0, 0));
        double sum1 = paymentEntities.stream().mapToDouble(PaymentEntity::getValue).sum();
        Double sum=sum1 + paymentDto.getValue();
        if (sum >= 100000) {
            comission = 5;
        } else if (sum>=10000) {
            comission = 3;
        }
        return comission*paymentDto.getValue()/100;
    }

}
