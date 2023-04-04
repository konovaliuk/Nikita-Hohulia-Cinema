package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.entities.Seat;

import java.sql.SQLException;
import java.util.List;

public interface SeatInterface {
    List<Seat> findByBookingId(long id);
    List<Seat> findByShowId(long id);
    void addSeat(Seat seat);
    void addSeat(List <Seat> seats);
    void deleteByBookingId(long id);
    void beginTransaction() throws SQLException;
    void endTransaction() throws SQLException;
    void rollbackTransaction() throws SQLException;
}
