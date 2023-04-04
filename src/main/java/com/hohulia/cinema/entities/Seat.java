package com.hohulia.cinema.entities;

import java.util.Objects;

public class Seat
{
    private long seatId;
    private long bookingId;
    private int seatPrice;
    private long showId;
    private int row;
    private int number;

    public Seat(){}
    public Seat(int row, int number, long BookingId, int SeatPrice, long ShowId)
    {
        this.row =row;
        this.number = number;
        this.bookingId = BookingId;
        this.seatPrice = SeatPrice;
        this.showId = ShowId;
    }
    public Seat(long seatId, int row, int number, long BookingId, int SeatPrice, long ShowId)
    {
        this.seatId = seatId;
        this.row =row;
        this.number = number;
        this.bookingId = BookingId;
        this.seatPrice = SeatPrice;
        this.showId = ShowId;
    }

    public long getSeatId() {
        return seatId;
    }
    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }
    public long getBookingId()
    {
        return bookingId;
    }
    public void setBookingId(long value)
    {
        bookingId = value;
    }
    public int getSeatPrice()
    {
        return seatPrice;
    }
    public void setSeatPrice(int value)
    {
        seatPrice = value;
    }
    public long getShowId()
    {
        return showId;
    }
    public void setShowId(long value)
    {
        showId = value;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return getSeatId()==seat.getSeatId() && getRow() == seat.getRow() && getBookingId() == seat.getBookingId() && getSeatPrice() == seat.getSeatPrice() && getShowId() == seat.getShowId() && getNumber() == seat.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeatId(), getRow(), getBookingId(), getSeatPrice(), getShowId(), getNumber());
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", row=" + row +
                ", number=" + number +
                ", bookingId=" + bookingId +
                ", seatPrice=" + seatPrice +
                ", showId=" + showId +
                '}';
    }
}
