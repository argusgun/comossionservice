package com.example.paymentservice.dto;

import com.example.paymentservice.entities.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long userId;
    private String phoneNumber;
    private LocalDateTime localDateTime;
    private Float value;

    public PaymentDto fromEntity(PaymentEntity paymentEntity){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(paymentEntity.getUser().getId());
        paymentDto.setPhoneNumber(paymentEntity.getUser().getPhoneNumber());
        paymentDto.setLocalDateTime(paymentEntity.getPaymentTime());
        paymentDto.setValue(paymentEntity.getValue());
        return paymentDto;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userId\": " + userId +
                ", \"phoneNumber\": \"" + phoneNumber + "\"" +
                ", \"localDateTime\":\"" + localDateTime+
                "\", \"value\": " + value +
                "}";
    }
}
