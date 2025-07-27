package com.rohit.controllers;

import com.rohit.models.Movie;
import com.rohit.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovie(@PathVariable long id) {
        return movieService.getMovieById(id);
    }
}

