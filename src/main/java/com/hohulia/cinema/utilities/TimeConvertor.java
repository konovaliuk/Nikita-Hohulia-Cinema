package com.hohulia.cinema.utilities;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.TimeZone;

public class TimeConvertor {
    public static java.sql.Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return java.sql.Date.valueOf(localDateTime.toLocalDate());
    }

    public static java.sql.Time convertLocalDateTimeToTime(LocalDateTime localDateTime) {
        return java.sql.Time.valueOf(localDateTime.toLocalTime());
    }

    public static LocalDateTime convertDateToLocalDateTime(java.sql.Date date) {
        return date.toLocalDate().atStartOfDay();
    }

    public static LocalDateTime convertTimeToLocalDateTime(java.sql.Time time) {
        return time.toLocalTime().atDate(LocalDate.now());
    }

    public static java.sql.Date getRightTimezone(java.sql.Date time) {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(System.currentTimeMillis());
        time.setTime(time.getTime() - offset);
        return time;
    }

    public static java.sql.Time getRightTimezone(java.sql.Time time) {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(System.currentTimeMillis());
        time.setTime(time.getTime() - offset);
        return time;
    }

    public static java.sql.Timestamp getRightTimezone(java.sql.Timestamp timestamp) {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(timestamp.getTime());
        timestamp.setTime(timestamp.getTime() - offset);
        return timestamp;
    }
    public static String toSqlString (LocalDateTime dt) {
        return TimeConvertor.convertLocalDateTimeToDate(dt) + " " + TimeConvertor.convertLocalDateTimeToTime(dt);
    }


}
