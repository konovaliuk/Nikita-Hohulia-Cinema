package com.nikitahohulia.Cinema.controller;

import com.nikitahohulia.Cinema.entities.Movie;
import com.nikitahohulia.Cinema.entities.Schedule;
import com.nikitahohulia.Cinema.services.MovieService;
import com.nikitahohulia.Cinema.services.ScheduleService;
import com.nikitahohulia.Cinema.utils.ImgPaths;
import com.nikitahohulia.Cinema.wrappers.ShowWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@AllArgsConstructor
public class ShowController {

    private MovieService movieService;
    private ScheduleService scheduleService;

    @GetMapping("/show/{movieId}")
    public String showSchedule(@PathVariable("movieId") Integer id, Model theModel) {

        ShowWrapper showWrapper = movieService.getShowPage(id);
        theModel.addAttribute("showWrapper", showWrapper);
        return "shows";

    }

}

