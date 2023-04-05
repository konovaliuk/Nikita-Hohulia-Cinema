package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.MovieService;
import com.hohulia.cinema.services.ScheduleService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.ImgPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommand implements ICommand {
    private final MovieService movieService = ServiceFactory.getMovieService();
    private final ScheduleService scheduleService = ServiceFactory.getScheduleService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String sMovieId = request.getParameter("movieId");
            int movieId = Integer.parseInt(sMovieId);
            ArrayList<List<Schedule>> schedule = scheduleService.getNearestShows(movieId);
            Movie movie = movieService.getMovieById(movieId);
            String path = ImgPaths.getImgPath(movie);


            request.setAttribute("movie", movie);
            request.setAttribute("path", path);
            request.setAttribute("scheduleToday", schedule.get(0));
            request.setAttribute("scheduleTomorrow", schedule.get(1));
            request.setAttribute("scheduleTheDayAfterTomorrow", schedule.get(2));
            return "/WEB-INF/shows.jsp";
        } catch (ServiceException | SQLException e){
            request.setAttribute("error", e.getMessage());
            return "!/error";
        }
    }
}
