package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.BookingInterface;
import com.hohulia.cinema.entities.Booking;
import com.hohulia.cinema.utilities.TimeConvertor;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookingDaoImp implements BookingInterface {
    private final Connection connection;

    enum COLUMN {
        BOOKING_ID("booking_id"), DATETIME("datetime"), USER_ID("user_id"), SHOW_ID("show_id");
        public final String NAME;
        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String SELECT_BOOKING_BY_ID = "SELECT * FROM booking WHERE booking_id = ?";
    private static final String CREATE_BOOKING = "INSERT INTO booking (datetime, user_id, show_id) VALUES (?, ?, ?)";
    private static final String FIND_ALL_BOOKINGS_WITH_USER_ID = "SELECT * FROM booking where user_id = ?";
    private static final String DELETE_BY_BOOKING_ID = "DELETE FROM booking WHERE booking_id = ?";


    public BookingDaoImp(Connection connection) {
        this.connection = connection;
    }

    public Booking getBooking(ResultSet resultSet) throws SQLException {
        long bookingId = resultSet.getLong(COLUMN.BOOKING_ID.NAME);
        java.sql.Timestamp datetime = TimeConvertor.getRightTimezone(resultSet.getTimestamp(COLUMN.DATETIME.NAME));
        long userId = resultSet.getLong(COLUMN.USER_ID.NAME);
        long showId = resultSet.getLong(COLUMN.SHOW_ID.NAME);
        return new Booking(bookingId, datetime, userId, showId);
    }


    @Override
    public Booking findById(long id) {
        Booking booking = null;
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    booking = getBooking(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Booking makeBooking(Booking booking) {
        try (PreparedStatement stmt = connection.prepareStatement(CREATE_BOOKING, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, booking.get());
            stmt.setLong(2, booking.getUserId());
            stmt.setLong(3, booking.getShowId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public List<Booking> findAllWithUserId(long userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_BOOKINGS_WITH_USER_ID)){
             stmt.setLong(1, userId);
             ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Booking booking = getBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_BOOKING_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
