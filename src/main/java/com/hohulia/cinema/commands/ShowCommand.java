package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.MovieService;
import com.hohulia.cinema.services.ScheduleService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.ImgPaths;
import com.hohulia.cinema.utilities.TimeConvertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ShowCommand implements ICommand{
    private final MovieService movieService = ServiceFactory.getMovieService();
    private final ScheduleService scheduleService = ServiceFactory.getScheduleService();

    private static final String MAIN = "/index.jsp";
    private static final String ERROR = "!/error";
    private static final String SHOWS = "/shows.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Movie movie;
        List<Schedule> scheduleToday;
        List<Schedule> scheduleTomorrow;
        List<Schedule> scheduleTheDayAfterTomorrow;
        String sMovieId = request.getParameter("movieId");
        int movieId;

        try{
            movieId = Integer.parseInt(sMovieId);

        } catch (Exception e){
            return MAIN;
        }

        try {
            LocalDate today = LocalDate.now();
            movie = movieService.getMovieById(movieId);
            scheduleToday = scheduleService.getScheduleByMovieId(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(1).plusHours(3))), movieId);
            scheduleTomorrow = scheduleService.getScheduleByMovieId(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(1).plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(2).plusHours(3))), movieId);
            scheduleTheDayAfterTomorrow = scheduleService.getScheduleByMovieId(Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(2).plusHours(3))), Timestamp.valueOf(TimeConvertor.toSqlString(today.atStartOfDay().plusDays(3).plusHours(3))), movieId);

        } catch (ServiceException e){
            return ERROR;
        }
        String path = ImgPaths.getImgPath(movie);

        System.out.println("Today");
        for (Schedule schedule: scheduleToday)
            System.out.println(schedule);
        System.out.println("Tomorrow");
        for (Schedule schedule: scheduleTomorrow)
            System.out.println(schedule);
        System.out.println("TheDayAfterTomorrow");
        for (Schedule schedule: scheduleTheDayAfterTomorrow)
            System.out.println(schedule);

        request.setAttribute("movie", movie);
        request.setAttribute("path", path);
        request.setAttribute("scheduleToday", scheduleToday);
        request.setAttribute("scheduleTomorrow", scheduleTomorrow);
        request.setAttribute("scheduleTheDayAfterTomorrow", scheduleTheDayAfterTomorrow);
        return SHOWS;

    }
}
