package com.hohulia.cinema.commands;

import com.hohulia.cinema.Constants;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.MovieService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.ImgPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class MoviesPageCommand implements ICommand{
    private final MovieService movieService = ServiceFactory.getMovieService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String pageNumberParam = request.getParameter("page");
            List<Movie> moviesForPage = movieService.getMovies(pageNumberParam);
            int totalPages = movieService.getTotalPages();
            Map<String, String> paths = ImgPaths.getImgPaths(moviesForPage);

            // Pass the moviesForPage list to your JSP for rendering
            request.setAttribute("movies", moviesForPage);
            request.setAttribute("currentPage", pageNumberParam);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("paths", paths);
            return "/WEB-INF/films.jsp";
        } catch (ServiceException e){
            request.setAttribute("error", e.getMessage());
            return "!/login";
        }

    }
}
