package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.dao.interfaces.SeatInterface;


import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeatDaoImp implements SeatInterface {
    private final Connection connection;

    private static final String SELECT_BY_BOOKING_ID = "SELECT * FROM seat WHERE booking_id = ?";
    private static final String SELECT_BY_SHOW_ID = "SELECT * FROM seat WHERE show_id = ?";
    private static final String ADD_SEAT = "INSERT INTO `cinema`.`seat` (`row`, `number`, `booking_id`, `seat_price`, `show_id`) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_BY_BOOKING_ID = "DELETE FROM seat WHERE booking_id = ?";

    enum COLUMN {
        SEAT_ID("seat_id"), BOOKING_ID("booking_id"), SEAT_PRICE("seat_price"), SHOW_ID("show_id"), ROW("row"), NUMBER("number");
        public final String NAME;

        COLUMN(String name) {
            this.NAME = name;
        }
    }


    public SeatDaoImp(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Seat getSeat(ResultSet resultSet) throws SQLException {
        long seatId = resultSet.getLong(COLUMN.SEAT_ID.NAME);
        long bookingId = resultSet.getLong(COLUMN.BOOKING_ID.NAME);
        int seatPrice = resultSet.getInt(COLUMN.SEAT_PRICE.NAME);
        long showId = resultSet.getLong(COLUMN.SHOW_ID.NAME);
        int row = resultSet.getInt(COLUMN.ROW.NAME);
        int number = resultSet.getInt(COLUMN.NUMBER.NAME);

        return new Seat(seatId, row, number, bookingId, seatPrice, showId);
    }


    @Override
    public List<Seat> findByBookingId(long id) {
        List<Seat> seats = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_BOOKING_ID)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Seat seat = getSeat(resultSet);
                    seats.add(seat);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }

    @Override
    public List<Seat> findByShowId(long id) {
        List<Seat> seats = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_SHOW_ID)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Seat seat = getSeat(resultSet);
                    seats.add(seat);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }

    @Override
    public void addSeat(Seat seat) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_SEAT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, seat.getRow());
            stmt.setInt(2, seat.getNumber());
            stmt.setLong(3, seat.getBookingId());
            stmt.setInt(4, seat.getSeatPrice());
            stmt.setLong(5, seat.getShowId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    seat.setSeatId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSeat(List<Seat> seats) {
        for (Seat seat : seats) {
            addSeat(seat);
        }
    }

    @Override
    public void deleteByBookingId(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_BOOKING_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error deleting booking with id " + id + ": " + ex.getMessage());
        }
    }

}

