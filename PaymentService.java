package com.anand.service;

import java.math.BigDecimal;

import com.anand.model.Payment;


public interface PaymentService {
    Payment createPayment(BigDecimal amount);
    

}


