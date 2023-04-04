package com.hohulia.cinema.utilities;

import com.hohulia.cinema.commands.*;
import com.hohulia.cinema.entities.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImgPaths {

    public static String getImgPath(Movie movie) {
        String path = "img/" + movie.getTitle() + ".jpg";
        return path;
    }

    public static Map<String, String> getImgPaths(List<Movie> movies) {
        Map<String, String> paths = new HashMap<>();
        for (Movie movie : movies) {
            String path = getImgPath(movie);
            paths.put(movie.getTitle(), path);
        }
        return paths;
    }
}
