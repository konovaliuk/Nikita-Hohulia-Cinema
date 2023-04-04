package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.*;

import java.sql.SQLException;
import java.util.List;
public interface BookingInterface {
    Booking findById(long id);
    Booking makeBooking(Booking booking);
    List<Booking> findAllWithUserId(long userId);
    void deleteById(long id);
    void beginTransaction() throws SQLException;
    void endTransaction() throws SQLException;
    void rollbackTransaction() throws SQLException;


}
