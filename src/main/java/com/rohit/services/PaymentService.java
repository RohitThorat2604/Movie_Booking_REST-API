package com.rohit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.models.Payment;
import com.rohit.repositories.BookingRepository;
import com.rohit.repositories.PaymentRepository;
import com.rohit.response_wrapper.MyResponseWrapper;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MyResponseWrapper myResponseWrapper;

    public ResponseEntity<?> processPayment(Long bookingId, Payment payment) {
        var bookingOpt = bookingRepository.findById(bookingId);

        if (bookingOpt.isEmpty()) {
            myResponseWrapper.setMessage("Invalid booking ID");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.BAD_REQUEST);
        }

        payment.setBooking(bookingOpt.get());

        if (payment.getPaymentTime() == null) {
            payment.setPaymentTime(java.time.LocalDateTime.now());
        }

        Payment savedPayment = paymentRepository.save(payment);
        myResponseWrapper.setMessage("Payment processed successfully");
        myResponseWrapper.setData(savedPayment);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getPaymentById(Long id) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        if (paymentOpt.isPresent()) {
            myResponseWrapper.setMessage("Payment found");
            myResponseWrapper.setData(paymentOpt.get());
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("Payment with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }
}

