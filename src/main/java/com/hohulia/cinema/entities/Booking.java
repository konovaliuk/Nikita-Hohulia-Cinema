package com.hohulia.cinema.entities;

import java.util.Objects;

public class Booking
{
    private long bookingId;
    private java.sql.Timestamp datetime;
    private long userId;
    private long showId;


    public Booking(){}
    public Booking(java.sql.Timestamp Datetime,long UserId,long ShowId)
    {
        this.datetime = Datetime;
        this.userId = UserId;
        this.showId = ShowId;
    }
    public Booking(long BookingId,java.sql.Timestamp Datetime,long UserId,long ShowId)
    {
        this.bookingId = BookingId;
        this.datetime = Datetime;
        this.userId = UserId;
        this.showId = ShowId;
    }

    public long getBookingId()
    {
        return bookingId;
    }
    public void setBookingId(long value)
    {
        bookingId = value;
    }
    public java.sql.Timestamp get()
    {
        return datetime;
    }
    public void set(java.sql.Timestamp value)
    {
        datetime = value;
    }
    public long getUserId()
    {
        return userId;
    }
    public void setUserId(long value) { userId = value;}
    public long getShowId()
    {
        return showId;
    }
    public void setShowId(long value)
    {
        showId = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return getBookingId() == booking.getBookingId() && getUserId() == booking.getUserId() && getShowId() == booking.getShowId() && datetime.equals(booking.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingId(), datetime, getUserId(), getShowId());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", datetime=" + datetime +
                ", userId=" + userId +
                ", showId=" + showId +
                '}';
    }
}