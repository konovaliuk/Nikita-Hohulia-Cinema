package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowShowId(Long showId);
}