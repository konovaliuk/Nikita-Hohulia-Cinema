package com.nikitahohulia.Cinema.utils;

import com.nikitahohulia.Cinema.entities.Movie;

import java.util.*;

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

    public static Map<String, String> getImgPaths(ArrayList<List<Movie>> movies) {
        Map<String, String> paths = new HashMap<>();
        for (List<Movie> list:movies){
            for (Movie movie : list) {
                String path = getImgPath(movie);
                paths.put(movie.getTitle(), path);
            }
        }

        return paths;
    }
}