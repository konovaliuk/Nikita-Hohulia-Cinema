package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.implementation.ScheduleDaoImp;
import com.hohulia.cinema.dao.implementation.SeatDaoImp;
import com.hohulia.cinema.dao.interfaces.BookingInterface;
import com.hohulia.cinema.dao.interfaces.MovieInterface;
import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.dao.interfaces.SeatInterface;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingService {
    BookingInterface bookingDao;
    SeatInterface seatDao;


    public List<Seat> getSeatsByShow(long id) throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            seatDao = new SeatDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }


        List<Seat> seats = seatDao.findByShowId(id);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return seats;
    }

    public List<Seat> getSeatsByBooking(long id) throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            seatDao = new SeatDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }


        List<Seat> seats = seatDao.findByBookingId(id);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return seats;
    }

}

