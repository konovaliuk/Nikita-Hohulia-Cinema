package com.nikitahohulia.Cinema.wrappers;

import com.nikitahohulia.Cinema.entities.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class MainPageWrapper {
    private Map<String, String> paths;
    private List<Movie> moviesToday;
    private List<Movie> moviesTomorrow;
    private List<Movie> moviesTheDayAfterTomorrow;
}
