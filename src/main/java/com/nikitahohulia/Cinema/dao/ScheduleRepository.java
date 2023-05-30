package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByStartTimeBetween(Instant startTime1, Instant startTime2);
    List<Schedule> findAllByMovieIdAndStartTimeBetween(Integer movieId, Instant startTime1, Instant startTime2);
}