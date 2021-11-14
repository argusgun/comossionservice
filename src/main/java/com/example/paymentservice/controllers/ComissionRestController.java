package com.example.paymentservice.controllers;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.services.ComissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/comissions")
@RequiredArgsConstructor
public class ComissionRestController {
    private final ComissionService comissionService;

    @GetMapping
    public ResponseEntity<Object> getComission(@RequestBody PaymentDto paymentDto){
        double comission = comissionService.getComissionPercent(paymentDto);
        return new ResponseEntity<>(comission, HttpStatus.OK);
    }

    @GetMapping("/1")
    public  ResponseEntity<Object> getPaymentDto(){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(1L);
        paymentDto.setLocalDateTime(LocalDateTime.now());
        paymentDto.setValue(10000d);
        return new ResponseEntity<>(paymentDto,HttpStatus.OK);
    }
}
