package com.nikitahohulia.Cinema.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Data
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "hall_id", nullable = false)
    private Integer hallId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "movie_id", nullable = false, referencedColumnName = "movie_id")
    private Movie movie;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @Override
    public String toString() {
        return "Schedule{" +
                "showId=" + showId +
                ", hallId=" + hallId +
                ", movieId=" + movieId +
                ", movie=" + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}