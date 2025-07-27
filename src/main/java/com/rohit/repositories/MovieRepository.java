package com.rohit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
