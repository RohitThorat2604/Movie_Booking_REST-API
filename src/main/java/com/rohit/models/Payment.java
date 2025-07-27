package com.rohit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Payment method is mandatory")
    private String paymentMethod;

    @NotNull(message = "Payment time is required")
    private LocalDateTime paymentTime;
}

