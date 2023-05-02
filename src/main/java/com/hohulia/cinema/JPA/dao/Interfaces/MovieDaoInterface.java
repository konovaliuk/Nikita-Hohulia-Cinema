package com.hohulia.cinema.JPA.dao.Interfaces;

import com.hohulia.cinema.JPA.Entities.Movie;

public interface MovieDaoInterface extends DAO<Movie>{
    Movie getByTitle(String title);
}
