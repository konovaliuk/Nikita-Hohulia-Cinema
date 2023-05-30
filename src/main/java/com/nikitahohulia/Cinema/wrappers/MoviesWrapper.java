package com.nikitahohulia.Cinema.wrappers;

import com.nikitahohulia.Cinema.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoviesWrapper {
    private Map<String, String> paths;
    private List<Movie> movies;
    int totalPages;
    int currentPage;
}
