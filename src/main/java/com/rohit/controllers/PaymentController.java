package com.rohit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.models.Payment;
import com.rohit.services.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

 
    @PostMapping("/payments/{bookingId}")
    public ResponseEntity<?> makePayment(@PathVariable Long bookingId, @RequestBody Payment payment) {
        return paymentService.processPayment(bookingId, payment);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPayment(@PathVariable long id) {
        return paymentService.getPaymentById(id);
    }
}

