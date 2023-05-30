package com.nikitahohulia.Cinema.wrappers;

import com.nikitahohulia.Cinema.entities.Movie;
import com.nikitahohulia.Cinema.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowWrapper {
    private Movie movie;
    private String path;
    private List<Schedule> scheduleToday;
    private List<Schedule> scheduleTomorrow;
    private List<Schedule> scheduleTheDayAfterTomorrow;
}
