package com.hohulia.cinema.JPA.dao.Interfaces;

import com.hohulia.cinema.JPA.Entities.Schedule;

import java.util.List;

public interface ScheduleDaoInterface extends DAO<Schedule> {
    List<Schedule> getAllRelevant();
    List<Schedule> getAllWithMovieId(int movieId);
}
