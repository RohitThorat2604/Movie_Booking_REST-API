package com.rohit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
