package com.nikitahohulia.Cinema.services;

import com.nikitahohulia.Cinema.dao.BookingRepository;
import com.nikitahohulia.Cinema.dao.SeatRepository;
import com.nikitahohulia.Cinema.entities.Seat;
import com.nikitahohulia.Cinema.wrappers.BookingWrapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private SeatRepository seatRepository;
    private BookingRepository bookingRepository;

    @Value("${hall.rows}")
    private int rows;
    @Value("${hall.columns}")
    private int columns;
    @Value("${seat.price}")
    private int seatPrice;

    public BookingService(SeatRepository seatRepository, BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
    }


    public int[][][] getSeatsByShow(long id) throws ServiceException {

        int[][][] seatArray;

        List<Seat> seatsList = seatRepository.findByShowShowId(id);
        seatsList.forEach(System.out::println);
        seatArray = new int[rows][columns][2];//2- price

        for (Seat seat : seatsList) {
            seatArray[seat.getRow() - 1][seat.getNumber() - 1][0] = 1; // seat is reserved
            seatArray[seat.getRow() - 1][seat.getNumber() - 1][1] = seat.getSeatPrice();
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(seatArray[i][j][0] + " ");
                if (seatArray[i][j][0] == 0)
                    seatArray[i][j][1] = seatPrice;
            }
            System.out.println();
        }
        return seatArray;
    }

    public BookingWrapper seatsByShow(long id) throws ServiceException {
        int[][][] seats= getSeatsByShow(id);
        return new BookingWrapper(id, seats);
    }


}

