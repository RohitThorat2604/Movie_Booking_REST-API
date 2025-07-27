package com.rohit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
