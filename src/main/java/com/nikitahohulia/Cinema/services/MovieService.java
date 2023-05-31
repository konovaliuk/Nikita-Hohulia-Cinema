package com.nikitahohulia.Cinema.services;

import com.nikitahohulia.Cinema.dao.MovieRepository;
import com.nikitahohulia.Cinema.entities.Movie;
import com.nikitahohulia.Cinema.entities.Schedule;
import com.nikitahohulia.Cinema.utils.ImgPaths;
import com.nikitahohulia.Cinema.wrappers.MainPageWrapper;
import com.nikitahohulia.Cinema.wrappers.MoviesWrapper;
import com.nikitahohulia.Cinema.wrappers.ShowWrapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private ScheduleService scheduleService;

    @Value("${page.size}")
    private int pageSize;
    @Autowired
    public MovieService(MovieRepository movieRepository, ScheduleService scheduleService) {
        this.movieRepository = movieRepository;
        this.scheduleService = scheduleService;
    }

    public Movie getMovieById(int id) throws ServiceException {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public List<Movie> getMovies(Integer pageNumber) throws ServiceException {

        pageNumber--;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.getContent();
    }

    public long getMoviesCount() throws ServiceException {
        return movieRepository.count();
    }

    public int getTotalPages() throws ServiceException {
        long moviesCount = getMoviesCount();
        return (int) Math.ceil((double) moviesCount / pageSize);
    }

     public List<Movie> getMoviesForSchedule(List<Schedule> schedule) throws ServiceException {

         List<Movie> movies = new ArrayList<>();
         Set<Integer> addedMovieIds = new HashSet<>();

         for (Schedule sc : schedule) {
             int movieId = sc.getMovieId();
             if (!addedMovieIds.contains(movieId)) { // check if movie ID has already been added
                 Movie movie = movieRepository.findById(movieId).get();
                 movies.add(movie);
                 addedMovieIds.add(movieId); // add the movie ID to the set of added movie IDs
             }
         }
         return movies;
     }


    public ArrayList<List<Movie>> getNearestMovies() throws ServiceException {
        ArrayList<List<Schedule>> schedules = scheduleService.getNearestShows(0);
        ArrayList<List<Movie>> movies = new ArrayList<>();

        for (List<Schedule> schedule: schedules)
            movies.add(getMoviesForSchedule(schedule));

        return movies;
    }

    public MainPageWrapper nearestMovies() throws ServiceException {

        ArrayList<List<Movie>> movies = getNearestMovies();
        Map<String, String> paths = ImgPaths.getImgPaths(movies);

        MainPageWrapper mainPageWrapper = new MainPageWrapper();
        mainPageWrapper.setPaths(paths);
        mainPageWrapper.setMoviesToday(movies.get(0));
        mainPageWrapper.setMoviesTomorrow(movies.get(1));
        mainPageWrapper.setMoviesTheDayAfterTomorrow(movies.get(2));
        return mainPageWrapper;
    }

    public MoviesWrapper getMoviesPage(Integer currentPage) throws ServiceException {
        if (currentPage==null)
            currentPage=1;
        List<Movie> movies = getMovies(currentPage);
        int totalPages = getTotalPages();
        Map<String, String> paths = ImgPaths.getImgPaths(movies);
        return new MoviesWrapper(paths, movies, totalPages, currentPage);
    }

    public ShowWrapper getShowPage(Integer id) throws ServiceException {
        ArrayList<List<Schedule>> schedule = scheduleService.getNearestShows(id);
        Movie movie = getMovieById(id);
        String path = ImgPaths.getImgPath(movie);
        return new ShowWrapper(movie, path, schedule.get(0), schedule.get(1), schedule.get(2));
    }


}