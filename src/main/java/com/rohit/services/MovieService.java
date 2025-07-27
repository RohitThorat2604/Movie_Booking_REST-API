package com.rohit.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.models.Movie;
import com.rohit.repositories.MovieRepository;
import com.rohit.response_wrapper.MyResponseWrapper;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MyResponseWrapper myResponseWrapper;

    public ResponseEntity<?> addMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        myResponseWrapper.setMessage("Movie added successfully");
        myResponseWrapper.setData(savedMovie);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        myResponseWrapper.setMessage("List of all movies");
        myResponseWrapper.setData(movies);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
    }

    public ResponseEntity<?> getMovieById(Long id) {
        Optional<Movie> movieOpt = movieRepository.findById(id);
        if (movieOpt.isPresent()) {
            myResponseWrapper.setMessage("Movie found");
            myResponseWrapper.setData(movieOpt.get());
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("Movie with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }
}

