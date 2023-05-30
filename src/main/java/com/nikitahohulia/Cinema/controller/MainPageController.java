package com.nikitahohulia.Cinema.controller;

import com.nikitahohulia.Cinema.services.MovieService;
import com.nikitahohulia.Cinema.wrappers.MainPageWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainPageController {

    private MovieService movieService;

    @GetMapping("/home")
    public String getNearestMovies(Model theModel){
        MainPageWrapper mainPageWrapper = movieService.nearestMovies();
        theModel.addAttribute("mainPageWrapper", mainPageWrapper);
        return "index";
    }

}
