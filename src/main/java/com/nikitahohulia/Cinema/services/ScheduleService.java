package com.nikitahohulia.Cinema.services;

import com.nikitahohulia.Cinema.dao.ScheduleRepository;
import com.nikitahohulia.Cinema.entities.Schedule;
import com.nikitahohulia.Cinema.utils.TimeConvertor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedule(Instant start, Instant end) throws ServiceException {

        List<Schedule> schedules;
        schedules = scheduleRepository.findAllByStartTimeBetween(start, end);
        return schedules;
    }

    public List<Schedule> getScheduleByMovieId(Instant start, Instant end, Integer id) throws ServiceException {

        List<Schedule> schedules;
        schedules = scheduleRepository.findAllByMovieIdAndStartTimeBetween(id, start, end);
        return schedules;
    }

    public List<Schedule> getScheduleWithOffset(int movieId, int addDays, int durDays) throws ServiceException {
        LocalDate time = LocalDate.now();
        LocalDateTime today = time.atStartOfDay().plusHours(3);
        if (addDays == 0) {
            LocalDateTime now = LocalDateTime.now().plusHours(3).minusHours(1);
            if(movieId!=0)
                return getScheduleByMovieId((Timestamp.valueOf(TimeConvertor.toSqlString(now))).toInstant(),
                        (Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1)))).toInstant(), movieId);
            return getSchedule((Timestamp.valueOf(TimeConvertor.toSqlString(now))).toInstant(),
                    (Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1)))).toInstant());
        }
        if(movieId!=0)
            return getScheduleByMovieId((Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(addDays)))).toInstant(),
                    (Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(addDays+durDays)))).toInstant(), movieId);
        return getSchedule((Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(1)))).toInstant(),
                (Timestamp.valueOf(TimeConvertor.toSqlString(today.plusDays(2)))).toInstant());

    }

    public ArrayList<List<Schedule>> getNearestShows(int movieId) throws ServiceException {
        ArrayList<List<Schedule>> schedule = new ArrayList<>();
        schedule.add(getScheduleWithOffset(movieId, 0, 1));
        schedule.add(getScheduleWithOffset(movieId, 1, 1));
        schedule.add(getScheduleWithOffset(movieId, 2, 1));
        System.out.println("getNearestShows");
        System.out.println(schedule);
        System.out.println("\n\n\n\n\n\n");
        return schedule;
    }


}
