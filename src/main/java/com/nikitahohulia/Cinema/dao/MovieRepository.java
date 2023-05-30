package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    long count();
}