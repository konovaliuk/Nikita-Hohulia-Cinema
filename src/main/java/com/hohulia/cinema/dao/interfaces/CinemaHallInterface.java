package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.CinemaHall;

import java.util.List;

public interface CinemaHallInterface {
    CinemaHall findById(long cinemaId, long hallId);
    List<CinemaHall> findAll();
    List<CinemaHall> findAllByCinemaId(long cinemaId);
    void addCinemaHall(CinemaHall user);
    void deleteById(long id);
}
