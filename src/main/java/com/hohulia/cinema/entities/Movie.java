package com.hohulia.cinema.entities;

import java.util.Objects;

public class Movie
{
    private int movieId;
    private java.sql.Time duration;
    private String title;
    private String description;

    public Movie(){}
    public Movie(java.sql.Time Duration,String Title,String Description)
    {
        this.duration = Duration;
        this.title = Title;
        this.description = Description;
    }
    public Movie(int MovieId,java.sql.Time Duration,String Title,String Description)
    {
        this.movieId = MovieId;
        this.duration = Duration;
        this.title = Title;
        this.description = Description;
    }

    public int getMovieId()
    {
        return movieId;
    }
    public void setMovieId(int value)
    {
        movieId = value;
    }
    public java.sql.Time getDuration()
    {
        return duration;
    }
    public void setDuration(java.sql.Time value)
    {
        duration = value;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String value)
    {
        title = value;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String value)
    {
        description = value;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getMovieId() == movie.getMovieId() && getDuration().equals(movie.getDuration()) && getTitle().equals(movie.getTitle()) && Objects.equals(getDescription(), movie.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getDuration(), getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}