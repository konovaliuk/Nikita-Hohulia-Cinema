package com.nikitahohulia.Cinema.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingWrapper {
    private Long showId;
    private int[][][] seats;

}
