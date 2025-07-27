package com.rohit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @Positive(message = "Duration must be positive")
    private int duration;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Release date is mandatory")
    private String releaseDate; 


}

