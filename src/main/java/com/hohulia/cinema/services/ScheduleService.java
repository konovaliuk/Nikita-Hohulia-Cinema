package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.implementation.ScheduleDaoImp;
import com.hohulia.cinema.dao.interfaces.MovieInterface;
import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ScheduleService {
    ScheduleInterface scheduleDao;

    public List<Schedule> getSchedule(Timestamp start, Timestamp end) throws ServiceException {


        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            scheduleDao = new ScheduleDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("ScheduleService - Failed to get database connection");
        }

        List<Schedule> schedules = scheduleDao.findByStartTimeBorders(start, end);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("ScheduleService - Failed to close database connection");
        }
        return schedules;
    }
    public List<Schedule> getScheduleByMovieId(Timestamp start, Timestamp end, int id) throws ServiceException {


        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            scheduleDao = new ScheduleDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("ScheduleService - Failed to get database connection");
        }

        List<Schedule> schedules = scheduleDao.findByStartTimeBordersAndMovieId(start, end, id);

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("ScheduleService - Failed to close database connection");
        }
        return schedules;
    }




}
