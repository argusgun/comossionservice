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

    public float getComissionPercent(PaymentDto paymentDto) {
        int comission = 1;
        final Float[] sum = {0f};
        UserEntity userEntity;
        if (paymentDto.getUserId() != null) {
            userEntity = userRepo.getById(paymentDto.getUserId());
        } else {
            userEntity = userRepo.findUserEntityByPhoneNumber(paymentDto.getPhoneNumber());
        }
        List<PaymentEntity> paymentEntities = paymentRepo.findAllByUserAndPaymentTimeAfter(userEntity, LocalDateTime.of(paymentDto.getLocalDateTime().getYear(), paymentDto.getLocalDateTime().getMonth(), 1, 0, 0));
                paymentEntities.stream().forEach(p -> {
            sum[0] = sum[0] + p.getValue();
        });
        sum[0]=sum[0] + paymentDto.getValue();
        if (sum[0].compareTo(100000f) > 0) {
            comission = 5;
        } else if (sum[0].compareTo(10000f) > 0) {
            comission = 3;
        }
        return comission*paymentDto.getValue()/100;
    }

}
