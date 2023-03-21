package com.hohulia.cinema.entities;

import java.util.Objects;

public class Seat
{
    private long seatId;
    private long bookingId;
    private int seatPrice;

    public Seat(){}
    public Seat(long SeatId,long BookingId,int SeatPrice)
    {
        this.seatId = SeatId;
        this.bookingId = BookingId;
        this.seatPrice = SeatPrice;
    }

    public long getSeatId()
    {
        return seatId;
    }
    public void setSeatId(long value)
    {
        seatId = value;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return getSeatId() == seat.getSeatId() && getBookingId() == seat.getBookingId() && getSeatPrice() == seat.getSeatPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeatId(), getBookingId(), getSeatPrice());
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", bookingId=" + bookingId +
                ", seatPrice=" + seatPrice +
                '}';
    }
}
