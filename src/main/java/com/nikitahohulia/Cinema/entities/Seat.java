package com.nikitahohulia.Cinema.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "booking_id")
    private Booking booking;

    @Column(name = "seat_price", nullable = false)
    private Integer seatPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "show_id", nullable = false, referencedColumnName = "show_id")
    private Booking show;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatPrice=" + seatPrice +
                ", row=" + row +
                ", number=" + number +
                '}';
    }
}