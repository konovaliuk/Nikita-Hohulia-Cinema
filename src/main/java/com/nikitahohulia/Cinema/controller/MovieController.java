package com.nikitahohulia.Cinema.controller;

import com.nikitahohulia.Cinema.services.MovieService;
import com.nikitahohulia.Cinema.wrappers.MoviesWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping("/movies")
    public String getMoviesForPage(@RequestParam(value = "page", required = false) Integer currentPage, Model theModel) {

        MoviesWrapper moviesWrapper = movieService.getMoviesPage(currentPage);
        theModel.addAttribute("moviesWrapper", moviesWrapper);

        return "movies";
    }


}
