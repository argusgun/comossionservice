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
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ComissionService {
    private final UserRepo userRepo;
    private final PaymentRepo paymentRepo;

    public int getComissionPercent(PaymentDto paymentDto) {
        int comission = 1;
        AtomicReference<Float> sum = new AtomicReference<>((float) 0);
        UserEntity userEntity;
        if (paymentDto.getUserId() != null) {
            userEntity = userRepo.getById(paymentDto.getUserId());
        } else {
            userEntity = userRepo.findUserEntityByPhoneNumber(paymentDto.getPhoneNumber());
        }
        List<PaymentEntity> paymentEntities = paymentRepo.findAllByUserAndPaymentTimeAfter(userEntity, LocalDateTime.of(paymentDto.getLocalDateTime().getYear(), paymentDto.getLocalDateTime().getMonth(), 1, 0, 0));
                paymentEntities.stream().forEach(p -> {
            sum.set(sum.get() + p.getValue());
        });
        sum.set(sum.get() + paymentDto.getValue());
        if (sum.get().compareTo(100000f) > 0) {
            comission = 5;
        } else if (sum.get().compareTo(10000f) > 0) {
            comission = 3;
        }
        return comission;
    }

}
