package com.anand.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

