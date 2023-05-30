package com.nikitahohulia.Cinema.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "description")
    private String description;
    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}