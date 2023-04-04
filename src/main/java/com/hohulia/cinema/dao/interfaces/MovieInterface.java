package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.Movie;

import java.util.List;

public interface MovieInterface {
    Movie findById(long id);
    Movie findByTitle(String title);
    List<Movie> findAll();
    void addMovie(Movie movie);
    void deleteById(long id);
    void deleteByTitle(String title);
    List<Movie> findMoviesWithOffsetAndLimit(int offset, int limit);
    int countRows();
}
