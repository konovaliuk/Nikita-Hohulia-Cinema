package com.nikitahohulia.Cinema.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`сinema_hall`")
public class СinemaHall {

    @Id
    @Column(name = "hall_id", nullable = false)
    private Integer hallId;

    @Column(name = "cinema_id", nullable = false)
    private Integer cinemaId;
    @Column(name = "seats_rows", nullable = false)
    private Integer seatsRows;

    @Column(name = "seats_columns", nullable = false)
    private Integer seatsColumns;

}