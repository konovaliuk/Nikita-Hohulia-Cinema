package com.hohulia.cinema.services;

import com.hohulia.cinema.Constants;
import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.interfaces.MovieInterface;
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

    public List<Movie> getMovies(String pageNumberParam) throws ServiceException {
        int pageSize = Constants.PAGE_SIZE;
        int pageNumber = 1;
        List<Movie> movies;

        if (pageNumberParam != null) {
            pageNumber = Integer.parseInt(pageNumberParam);
        }

        try ( Connection conn = DBCPDataSource.getConnection()) {
            movieDao = new MovieDaoImp(conn);
            movies = movieDao.findMoviesWithOffsetAndLimit((pageNumber-1)*pageSize, pageSize);
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return movies;
    }

    public int getMoviesCount() throws ServiceException {

        int moviesCount;

        try ( Connection conn = DBCPDataSource.getConnection()) {
            movieDao = new MovieDaoImp(conn);
            moviesCount = movieDao.countRows();
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return moviesCount;
    }

    public int getTotalPages() throws ServiceException {
        return (int) Math.ceil((double) getMoviesCount() / Constants.PAGE_SIZE);
    }

    public List<Movie> getMoviesForSchedule(List<Schedule> schedule) throws ServiceException {

        List<Movie> movies = new ArrayList<>();
        Set<Integer> addedMovieIds = new HashSet<>();


        try ( Connection conn = DBCPDataSource.getConnection()) {
            movieDao = new MovieDaoImp(conn);

            for (Schedule sc : schedule) {
                int movieId = sc.getMovieId();
                if (!addedMovieIds.contains(movieId)) { // check if movie ID has already been added
                    Movie movie = movieDao.findById(movieId);
                    movies.add(movie);
                    addedMovieIds.add(movieId); // add the movie ID to the set of added movie IDs
                }
            }

        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return movies;
    }

    public Movie getMovieById(int id) throws ServiceException, SQLException {

        Movie movie;

        try ( Connection conn = DBCPDataSource.getConnection()) {
            movieDao = new MovieDaoImp(conn);
            movie = movieDao.findById(id);
        } catch (SQLException e){
            throw new ServiceException("MovieService - Failed to get/close database connection");
        }

        return movie;
    }

    public ArrayList<List<Movie>> getNearestMovies() throws ServiceException {
        ScheduleService scheduleService = ServiceFactory.getScheduleService();
        ArrayList<List<Schedule>> schedules = scheduleService.getNearestShows(0);
        ArrayList<List<Movie>> movies = new ArrayList<>();

        for (List<Schedule> schedule: schedules)
            movies.add(getMoviesForSchedule(schedule));

        return movies;
    }


}
