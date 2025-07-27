package com.rohit.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rohit.models.Booking;
import com.rohit.models.Movie;
import com.rohit.models.User;
import com.rohit.repositories.BookingRepository;
import com.rohit.repositories.MovieRepository;
import com.rohit.repositories.UserRepository;
import com.rohit.response_wrapper.MyResponseWrapper;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MyResponseWrapper myResponseWrapper;

    public ResponseEntity<?> createBooking(Booking booking) {
      
        Long userId = booking.getUser().getId();
        Long movieId = booking.getMovie().getMovieId();

        Optional<User> userOption = userRepository.findById(userId);
        Optional<Movie> movieOption = movieRepository.findById(movieId);

        if (userOption.isEmpty() || movieOption.isEmpty()) {
            myResponseWrapper.setMessage("Invalid user or movie ID");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.BAD_REQUEST);
        }

        booking.setUser(userOption.get());
        booking.setMovie(movieOption.get());
        booking.setBookingDate(Instant.now());

        Booking savedBooking = bookingRepository.save(booking);

        myResponseWrapper.setMessage("Booking created successfully");
        myResponseWrapper.setData(savedBooking);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        myResponseWrapper.setMessage("List of all bookings");
        myResponseWrapper.setData(bookings);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
    }

    public ResponseEntity<?> getBookingById(Long id) {
        Optional<Booking> bookingOption = bookingRepository.findById(id);
        if (bookingOption.isPresent()) {
            myResponseWrapper.setMessage("Booking found");
            myResponseWrapper.setData(bookingOption.get());
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("Booking with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            myResponseWrapper.setMessage("Booking deleted successfully");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("Booking with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }
}

