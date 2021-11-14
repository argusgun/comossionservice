package com.example.paymentservice.controllers;

import com.example.paymentservice.dto.PaymentDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ComissionRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getComission1() throws Exception {
        this.mockMvc.perform(get("/comissions")
                .content(getPaymentDto1().toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value("300.0"));
    }

    @Test
    void getComission2() throws Exception {
        this.mockMvc.perform(get("/comissions")
                .content(getPaymentDto2().toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value("500.0"));
    }

    @Test
    void getComission3() throws Exception {
        this.mockMvc.perform(get("/comissions")
                .content(getPaymentDto3().toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value("10.0"));
    }

    private PaymentDto getPaymentDto1() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(1L);
        paymentDto.setValue(10000d);
        paymentDto.setLocalDateTime(LocalDateTime.of(2021, 10, 21, 15, 10, 0));
        return paymentDto;
    }

    private PaymentDto getPaymentDto2() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(2L);
        paymentDto.setValue(10000d);
        paymentDto.setLocalDateTime(LocalDateTime.of(2021, 10, 21, 15, 10, 0));
        return paymentDto;
    }

    private PaymentDto getPaymentDto3() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPhoneNumber("+79181234567");
        paymentDto.setValue(1000d);
        paymentDto.setLocalDateTime(LocalDateTime.of(2021, 10, 21, 15, 10, 0));
        return paymentDto;
    }
}