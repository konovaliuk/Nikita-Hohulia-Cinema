package com.hohulia.cinema.dao.interfaces;


import com.hohulia.cinema.entities.Schedule;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleInterface {
    Schedule findById(long id);
    List<Schedule> findByMovieId(int id);
    List<Schedule> findAll();
    void addSchedule(Schedule movie);
    void deleteById(long id);

    List<Schedule> findByStartTimeBorders(java.sql.Timestamp start, java.sql.Timestamp end);

    List<Schedule> findByStartTimeBordersAndMovieId(Timestamp start, Timestamp end, int id);
}
