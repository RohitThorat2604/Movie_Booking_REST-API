package com.rohit.controllers;

import com.rohit.models.Booking;
import com.rohit.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBooking(@PathVariable long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable long id) {
        return bookingService.deleteBooking(id);
    }
}
