package com.hohulia.cinema.services;

import com.hohulia.cinema.Constants;
import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.BookingDaoImp;
import com.hohulia.cinema.dao.implementation.SeatDaoImp;
import com.hohulia.cinema.dao.interfaces.BookingInterface;
import com.hohulia.cinema.dao.interfaces.SeatInterface;
import com.hohulia.cinema.entities.Booking;
import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hohulia.cinema.Constants.DEFAULT_PRICE;

public class BookingService {
    BookingInterface bookingDao;
    SeatInterface seatDao;


    public int[][][] getSeatsByShow(long id, int numRows, int numCols) throws ServiceException {

        int[][][] seatArray;
        try ( Connection conn = DBCPDataSource.getConnection()) {
            seatDao = new SeatDaoImp(conn);
            List<Seat> seatsList = seatDao.findByShowId(id);

            seatArray = new int[numRows][numCols][2];//2- price

            for (Seat seat : seatsList) {
                seatArray[seat.getRow() - 1][seat.getNumber() - 1][0] = 1; // seat is reserved
                seatArray[seat.getRow() - 1][seat.getNumber() - 1][1] = seat.getSeatPrice();
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    System.out.print(seatArray[i][j][0] + " ");
                    if(seatArray[i][j][0]==0)
                        seatArray[i][j][1] = DEFAULT_PRICE;
                }
                System.out.println();
            }


        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return seatArray;
    }

    public List<Seat> getSeatsByBooking(long id) throws ServiceException {

        List<Seat> seats;
        try ( Connection conn = DBCPDataSource.getConnection()) {
            seatDao = new SeatDaoImp(conn);
            seats = seatDao.findByBookingId(id);
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return seats;
    }

    public List <Booking> getBookingsByUserId(long userId) throws ServiceException {
        List <Booking> bookings = null;
        try ( Connection conn = DBCPDataSource.getConnection()) {
            bookingDao = new BookingDaoImp(conn);
            bookings = bookingDao.findAllWithUserId(userId);
            System.out.println(bookings.toString());
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return bookings;
    }

    public List<List<Seat>> getSeatsByBooking(List<Booking> bookings) throws ServiceException {

        List<List<Seat>> seats = new ArrayList<>();
        try ( Connection conn = DBCPDataSource.getConnection()) {
            seatDao = new SeatDaoImp(conn);
            for(Booking booking: bookings) {
                seats.add(seatDao.findByBookingId(booking.getBookingId()));
            }
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return seats;
    }


}

