package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.*;

import java.sql.SQLException;
import java.util.List;
public interface BookingInterface {
    Booking findById(long id);
    Booking makeBooking(Booking booking);
    List<Booking> findAllWithUserId(long userId) throws SQLException;
    void deleteById(long id);



}
