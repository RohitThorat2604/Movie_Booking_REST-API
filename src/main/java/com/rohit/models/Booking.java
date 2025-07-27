package com.rohit.models;

import java.time.Instant;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @CreatedDate
    private Instant bookingDate;

    @Min(value = 1, message = "Number of tickets must be at least 1")
    private int nooftickets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne(mappedBy = "booking")
    @JsonIgnore
    private Payment payment;
}

