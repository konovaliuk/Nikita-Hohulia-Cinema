package com.hohulia.cinema.entities;

import java.util.Objects;

public class Schedule
{
    private long showId;
    private int movieId;
    private int hallId;
    private java.sql.Date startTime;
    private java.sql.Date endTime;

    public Schedule(){}
    public Schedule(int MovieId,int HallId,java.sql.Date StartTime,java.sql.Date EndTime)
    {
        this.movieId = MovieId;
        this.hallId = HallId;
        this.startTime = StartTime;
        this.endTime = EndTime;
    }
    public Schedule(long ShowId,int MovieId,int HallId,java.sql.Date StartTime,java.sql.Date EndTime)
    {
        this.showId = ShowId;
        this.movieId = MovieId;
        this.hallId = HallId;
        this.startTime = StartTime;
        this.endTime = EndTime;
    }

    public long getShowId()
    {
        return showId;
    }
    public void setShowId(long value)
    {
        showId = value;
    }
    public int getMovieId()
    {
        return movieId;
    }
    public void setMovieId(int value)
    {
        movieId = value;
    }
    public int getHallId()
    {
        return hallId;
    }
    public void setHallId(int value)
    {
        hallId = value;
    }
    public java.sql.Date getStartTime()
    {
        return startTime;
    }
    public void setStartTime(java.sql.Date value)
    {
        startTime = value;
    }
    public java.sql.Date getEndTime()
    {
        return endTime;
    }
    public void setEndTime(java.sql.Date value)
    {
        endTime = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getShowId() == schedule.getShowId() && getMovieId() == schedule.getMovieId() && getHallId() == schedule.getHallId() && getStartTime().equals(schedule.getStartTime()) && getEndTime().equals(schedule.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShowId(), getMovieId(), getHallId(), getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "showId=" + showId +
                ", movieId=" + movieId +
                ", hallId=" + hallId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
