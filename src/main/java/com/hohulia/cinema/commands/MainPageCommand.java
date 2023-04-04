package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.MovieService;
import com.hohulia.cinema.services.ScheduleService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.ImgPaths;
import com.hohulia.cinema.utilities.TimeConvertor;
import com.hohulia.cinema.utilities.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
public class MainPageCommand implements ICommand{
    private final MovieService movieService = ServiceFactory.getMovieService();
    private final ScheduleService scheduleService = ServiceFactory.getScheduleService();
    private static final String MAIN = "/index.jsp";
    private static final String ERROR = "!/error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Movie> moviesToday;
        List<Movie> moviesTomorrow;
        List<Movie> moviesTheDayAfterTomorrow;
        List<Schedule> scheduleToday;
        List<Schedule> scheduleTomorrow;
        List<Schedule> scheduleTheDayAfterTomorrow;

        try {
            LocalDate today = LocalDate.now();
            scheduleToday = scheduleService.getSchedule(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(1).plusHours(3))));
            scheduleTomorrow = scheduleService.getSchedule(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(1).plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(2).plusHours(3))));
            scheduleTheDayAfterTomorrow = scheduleService.getSchedule(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(2).plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(3).plusHours(3))));

            moviesToday = movieService.getMoviesForSchedule(scheduleToday);
            moviesTomorrow = movieService.getMoviesForSchedule(scheduleTomorrow);
            moviesTheDayAfterTomorrow = movieService.getMoviesForSchedule(scheduleTheDayAfterTomorrow);

        }catch(ServiceException e) {
            return ERROR;
        }

        Set<Movie> allMovies = new HashSet<>();
        allMovies.addAll(moviesToday);
        allMovies.addAll(moviesTomorrow);
        allMovies.addAll(moviesTheDayAfterTomorrow);

        Map<String, String> paths = ImgPaths.getImgPaths(new ArrayList<>(allMovies));
        request.setAttribute("paths", paths);
        request.setAttribute("moviesToday", moviesToday);
        request.setAttribute("moviesTomorrow", moviesTomorrow);
        request.setAttribute("moviesTheDayAfterTomorrow", moviesTheDayAfterTomorrow);
        return MAIN;
    }
}
