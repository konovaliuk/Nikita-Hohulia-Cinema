package com.hohulia.cinema.JPA.dao.Interfaces;

import com.hohulia.cinema.JPA.Entities.Booking;
import com.hohulia.cinema.JPA.Entities.UserList;

import java.util.List;

public interface BookingDaoInterface  extends DAO<Booking> {
    List<Booking> getByUserId(long userId);
}
