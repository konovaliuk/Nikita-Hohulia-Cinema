package com.hohulia.cinema.entities;

import java.util.Objects;

public class CinemaHall
{
    private int hallId;
    private int cinemaId;
    private int seatsRows;
    private int seatsColumns;


    public CinemaHall(){}
    public CinemaHall(int CinemaId, int SeatsRows, int SeatsColumns)
    {
        this.cinemaId = CinemaId;
        this.seatsRows = SeatsRows;
        this.seatsColumns = SeatsColumns;
    }
    public CinemaHall(int HallId, int CinemaId, int SeatsRows, int SeatsColumns)
    {
        this.hallId = HallId;
        this.cinemaId = CinemaId;
        this.seatsRows = SeatsRows;
        this.seatsColumns = SeatsColumns;
    }
    public int getHallId()
    {
        return hallId;
    }
    public void setHallId(int value)
    {
        hallId = value;
    }
    public int getCinemaId()
    {
        return cinemaId;
    }
    public void setCinemaId(int value)
    {
        cinemaId = value;
    }
    public int getSeatsRows()
    {
        return seatsRows;
    }
    public void setSeatsRows(int value)
    {
        seatsRows = value;
    }
    public int getSeatsColumns()
    {
        return seatsColumns;
    }
    public void setSeatsColumns(int value)
    {
        seatsColumns = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return getHallId() == that.getHallId() && getCinemaId() == that.getCinemaId() && getSeatsRows() == that.getSeatsRows() && getSeatsColumns() == that.getSeatsColumns();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHallId(), getCinemaId(), getSeatsRows(), getSeatsColumns());
    }

    @Override
    public String toString() {
        return "Cinema_hall{" +
                "hallId=" + hallId +
                ", cinemaId=" + cinemaId +
                ", seatsRows=" + seatsRows +
                ", seatsColumns=" + seatsColumns +
                '}';
    }
}
