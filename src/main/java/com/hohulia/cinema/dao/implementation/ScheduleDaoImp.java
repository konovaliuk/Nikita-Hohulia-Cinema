package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.utilities.TimeConvertor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImp implements ScheduleInterface {
    private final Connection connection;

    enum COLUMN {
        SHOW_ID("show_id"), MOVIE_ID("movie_id"), HALL_ID("hall_id"), START_TIME("start_time"), END_TIME("end_time");
        public final String NAME;

        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String SELECT_BY_ID = "SELECT * FROM schedule WHERE show_id = ?";
    private static final String SELECT_BY_MOVIE_ID = "SELECT * FROM schedule WHERE movie_id = ?";
    private static final String ADD_SCHEDULE = "INSERT INTO `cinema`.`schedule` (`movie_id`, `hall_id`, `start_time`, `end_time`) VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM schedule";
    private static final String DELETE_BY_ID = "DELETE FROM schedule WHERE show_id = ?";
    private static final String SELECT_BY_START_TIME = "SELECT * FROM cinema.schedule where start_time >= ? and start_time <= ? ORDER BY start_time ASC";//'2023-04-01 19:00:00'
    private static final String SELECT_BY_START_TIME_AND_MOVIE_ID = "SELECT * FROM cinema.schedule where start_time >= ? and start_time <= ? and movie_id=? ORDER BY start_time ASC";//'2023-04-01 19:00:00'

    public ScheduleDaoImp(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Schedule getSchedule(ResultSet resultSet) throws SQLException {
        long showId = resultSet.getLong(COLUMN.SHOW_ID.NAME);
        int movieId = resultSet.getInt(COLUMN.MOVIE_ID.NAME);
        int hallId = resultSet.getInt(COLUMN.HALL_ID.NAME);
        java.sql.Timestamp startTime = TimeConvertor.getRightTimezone(resultSet.getTimestamp(COLUMN.START_TIME.NAME));
        java.sql.Timestamp endTime = TimeConvertor.getRightTimezone(resultSet.getTimestamp(COLUMN.END_TIME.NAME));
        return new Schedule(showId, movieId, hallId, startTime, endTime);
    }

    @Override
    public Schedule findById(long id) {
        Schedule schedule = new Schedule();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    schedule = getSchedule(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedule;
    }

    @Override
    public List<Schedule> findByMovieId(int id) {
        List<Schedule> schedules = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_MOVIE_ID)) {
            stmt.setInt(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = getSchedule(resultSet);
                    schedules.add(schedule);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                Schedule schedule = getSchedule(resultSet);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_SCHEDULE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, schedule.getMovieId());
            stmt.setInt(2, schedule.getHallId());
            stmt.setTimestamp(3, schedule.getStartTime());
            stmt.setTimestamp(4, schedule.getEndTime());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    schedule.setShowId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating a new schedule failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding schedule: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Deleting the schedule failed.");
        }
    }

    @Override
    public List<Schedule> findByStartTimeBorders(java.sql.Timestamp start, java.sql.Timestamp end) {
        List<Schedule> schedules = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_START_TIME)) {
            stmt.setTimestamp(1, start);
            stmt.setTimestamp(2, end);

            System.out.println(stmt.toString());

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = getSchedule(resultSet);
                    schedules.add(schedule);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public List<Schedule> findByStartTimeBordersAndMovieId(java.sql.Timestamp start, java.sql.Timestamp end, int id) {
        List<Schedule> schedules = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_START_TIME_AND_MOVIE_ID)) {
            stmt.setTimestamp(1, start);
            stmt.setTimestamp(2, end);
            stmt.setInt(3, id);

            System.out.println(stmt.toString());


            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = getSchedule(resultSet);
                    schedules.add(schedule);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

}

