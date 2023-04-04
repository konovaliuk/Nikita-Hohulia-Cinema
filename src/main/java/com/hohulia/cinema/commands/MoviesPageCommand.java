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

    private static final String FILMS = "/films.jsp";
    private static final String ERROR = "!/error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Movie> moviesForPage;
        int pageNumber = 1; // default to first page
        int pageSize = Constants.PAGE_SIZE; // default to 6 movies per page
        int totalPages = 1;

        String pageNumberParam = request.getParameter("page");
        System.out.println("MoviePageCommand; String page="+pageNumberParam);

        if (pageNumberParam != null) {
            pageNumber = Integer.parseInt(pageNumberParam);
        }
        System.out.println("MoviePageCommand; int page="+pageNumber);

        try {
            moviesForPage = movieService.getMovies(pageNumber, pageSize);
            totalPages = (int) Math.ceil((double) movieService.getMoviesCount() / pageSize) ;
        }catch(ServiceException e) {
            return ERROR;
        }

        Map<String, String> paths = ImgPaths.getImgPaths(moviesForPage);

        for (Movie movie: moviesForPage)
            System.out.println(movie);

        // Pass the moviesForPage list to your JSP for rendering
        request.setAttribute("movies", moviesForPage);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("paths", paths);
        return FILMS;

    }
}
