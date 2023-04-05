package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.implementation.ScheduleDaoImp;
import com.hohulia.cinema.dao.interfaces.MovieInterface;
import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.utilities.TimeConvertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    ScheduleInterface scheduleDao;

    public List<Schedule> getSchedule(Timestamp start, Timestamp end) throws ServiceException {

        List<Schedule> schedules;

        try ( Connection conn = DBCPDataSource.getConnection()) {
            scheduleDao = new ScheduleDaoImp(conn);
            schedules = scheduleDao.findByStartTimeBorders(start, end);
        } catch (SQLException e){
            throw new ServiceException("ScheduleService - Failed to get/close database connection");
        }

        return schedules;
    }
    public List<Schedule> getScheduleByMovieId(Timestamp start, Timestamp end, int id) throws ServiceException {

        List<Schedule> schedules;

        try ( Connection conn = DBCPDataSource.getConnection()) {
            scheduleDao = new ScheduleDaoImp(conn);
            schedules = scheduleDao.findByStartTimeBordersAndMovieId(start, end, id);
        } catch (SQLException e){
            throw new ServiceException("ScheduleService - Failed to get/close database connection");
        }

        return schedules;
    }

    public List<Schedule> getScheduleWithOffset(int movieId, int addDays, int durDays) throws ServiceException {
        LocalDate time = LocalDate.now();
        LocalDateTime today = time.atStartOfDay().plusHours(3);
        if (addDays == 0) {
            LocalDateTime now = LocalDateTime.now().plusHours(3).minusHours(1);
            if(movieId!=0)
                return getScheduleByMovieId(Timestamp.valueOf(TimeConvertor.toSqlString(now)), Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1))), movieId);
            return getSchedule(Timestamp.valueOf(TimeConvertor.toSqlString(now)), Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1))));
        }
        if(movieId!=0)
            return getScheduleByMovieId(Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(addDays))), Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(addDays+durDays))), movieId);
        return getSchedule(Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1))), Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(2))));

    }


    public ArrayList<List<Schedule>> getNearestShows(int movieId) throws ServiceException {
        ArrayList<List<Schedule>> schedule = new ArrayList<>();
        schedule.add(getScheduleWithOffset(movieId, 0, 1));
        schedule.add(getScheduleWithOffset(movieId, 1, 1));
        schedule.add(getScheduleWithOffset(movieId, 2, 1));
        return schedule;
    }




}
