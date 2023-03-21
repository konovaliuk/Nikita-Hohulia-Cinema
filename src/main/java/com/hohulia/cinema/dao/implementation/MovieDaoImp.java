package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.MovieInterface;
import com.hohulia.cinema.entities.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImp implements MovieInterface {
    private final Connection connection;

    enum COLUMN {
        MOVIE_ID("movie_id"), DURATION("duration"), TITLE("title"), DESCRIPTION("description");
        public final String NAME;

        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String SELECT_BY_ID = "SELECT * FROM movie WHERE movie_id = ?";
    private static final String SELECT_BY_TITLE = "SELECT * FROM movie WHERE title = ?";
    private static final String ADD_MOVIE = "INSERT INTO `cinema`.`movie` (`duration`, `title`, `description`) VALUES (?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM movie";
    private static final String DELETE_BY_ID = "DELETE FROM movie WHERE movie_id = ?";
    private static final String DELETE_BY_TITLE = "DELETE FROM movie WHERE title = ?";


    public MovieDaoImp(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Movie getMovie(ResultSet resultSet) throws SQLException {
        int movieID = resultSet.getInt(COLUMN.MOVIE_ID.NAME);
        java.sql.Time duration = resultSet.getTime(COLUMN.DURATION.NAME);
        String title = resultSet.getString(COLUMN.TITLE.NAME);
        String description = resultSet.getString(COLUMN.DESCRIPTION.NAME);
        return new Movie(movieID, duration, title, description);
    }

    @Override
    public Movie findById(long id) {
        Movie movie = new Movie();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    movie = getMovie(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public Movie findByTitle(String title) {
        Movie movie = new Movie();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_TITLE)) {
            stmt.setString(1, title);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    movie = getMovie(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                Movie movie = getMovie(resultSet);
                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    @Override
    public void addMovie(Movie movie) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_MOVIE)) {
            stmt.setTime(1, movie.getDuration());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getDescription());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByTitle(String title) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_TITLE)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
