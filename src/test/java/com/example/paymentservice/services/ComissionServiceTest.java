package com.example.paymentservice.services;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.entities.PaymentEntity;
import com.example.paymentservice.entities.UserEntity;
import com.example.paymentservice.repos.PaymentRepo;
import com.example.paymentservice.repos.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ComissionServiceTest {
    @MockBean
    private UserRepo userRepo;
    @MockBean
    private PaymentRepo paymentRepo;
    @Autowired
    private ComissionService comissionService;
    @Test
    void getComissionPercentWithUserId() {
        PaymentDto paymentDto1 = getPaymentDto1();
        ArrayList<PaymentEntity> paymentEntities = new ArrayList<>();
        paymentEntities.add(getPaymentEntity1());
        paymentEntities.add(getPaymentEntity2());
        UserEntity userEntity=getUserEntity();
        Mockito.doReturn(userEntity).when(userRepo).getById(paymentDto1.getUserId());
        Mockito.doReturn(paymentEntities).when(paymentRepo).findAllByUserAndPaymentTimeAfter(userEntity,LocalDateTime.of(paymentDto1.getLocalDateTime().getYear(),paymentDto1.getLocalDateTime().getMonth(),1,0,0));
        Integer comission = comissionService.getComissionPercent(paymentDto1);
        Mockito.verify(userRepo,Mockito.times(1)).getById(paymentDto1.getUserId());
        Mockito.verify(userRepo,Mockito.times(0)).findUserEntityByPhoneNumber(paymentDto1.getPhoneNumber());
        Assert.assertEquals(new Integer(5),comission);

    }

    @Test
    void getComissionPercentWithUserPhoneNumber() {
        PaymentDto paymentDto2 = getPaymentDto2();
        ArrayList<PaymentEntity> paymentEntities = new ArrayList<>();
        paymentEntities.add(getPaymentEntity1());
        UserEntity userEntity=getUserEntity();
        Mockito.doReturn(userEntity).when(userRepo).findUserEntityByPhoneNumber(paymentDto2.getPhoneNumber());
        Mockito.doReturn(paymentEntities).when(paymentRepo).findAllByUserAndPaymentTimeAfter(userEntity,LocalDateTime.of(paymentDto2.getLocalDateTime().getYear(),paymentDto2.getLocalDateTime().getMonth(),1,0,0));
        Integer comission = comissionService.getComissionPercent(paymentDto2);
        Mockito.verify(userRepo,Mockito.times(0)).getById(paymentDto2.getUserId());
        Mockito.verify(userRepo,Mockito.times(1)).findUserEntityByPhoneNumber(paymentDto2.getPhoneNumber());
        Assert.assertEquals(new Integer(3),comission);


    }

    private PaymentDto getPaymentDto1() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(1L);
        paymentDto.setValue(10000f);
        paymentDto.setLocalDateTime(LocalDateTime.of(2021, 10, 21, 15, 10, 0));
        return paymentDto;
    }

    private PaymentDto getPaymentDto2() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPhoneNumber("+79181234567");
        paymentDto.setValue(1000f);
        paymentDto.setLocalDateTime(LocalDateTime.of(2021, 10, 21, 15, 10, 0));
        return paymentDto;
    }

    private PaymentEntity getPaymentEntity1(){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(2L);
        paymentEntity.setPaymentTime(LocalDateTime.of(2021,10,10,10,10,10));
        paymentEntity.setValue(10000f);
        return paymentEntity;
    }

    private PaymentEntity getPaymentEntity2(){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(3L);
        paymentEntity.setPaymentTime(LocalDateTime.of(2021,10,10,10,10,10));
        paymentEntity.setValue(91000f);
        return paymentEntity;
    }

    private UserEntity getUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setPhoneNumber("+79168585858");
        userEntity.setName("user1");
        return userEntity;
    }

}