package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.implementation.ScheduleDaoImp;
import com.hohulia.cinema.dao.interfaces.MovieInterface;
import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.exceptions.ServiceException;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MovieService {
    MovieInterface movieDao;


    public List<Movie> getMovies(int pageNumber, int pageSize) throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            movieDao = new MovieDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }



        System.out.println("getMovies(pageNumber, pageSize): " + pageNumber + ", " + pageSize);
        List<Movie> movies = movieDao.findMoviesWithOffsetAndLimit((pageNumber-1)*pageSize, pageSize);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return movies;
    }

    public int getMoviesCount() throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            movieDao = new MovieDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }

        int moviesCount = movieDao.countRows();

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return moviesCount;
    }

    public List<Movie> getMoviesForSchedule(List<Schedule> schedule) throws ServiceException {

        List<Movie> movies = new ArrayList<>();
        Set<Integer> addedMovieIds = new HashSet<>();
        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            movieDao = new MovieDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }

        for (Schedule sc : schedule) {
            int movieId = sc.getMovieId();
            if (!addedMovieIds.contains(movieId)) { // check if movie ID has already been added
                Movie movie = movieDao.findById(movieId);
                movies.add(movie);
                addedMovieIds.add(movieId); // add the movie ID to the set of added movie IDs
            }
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return movies;
    }

    public Movie getMovieById(int id) throws ServiceException {


        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            movieDao = new MovieDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to get database connection");
        }

        Movie movie = movieDao.findById(id);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }
        return movie;
    }

}
