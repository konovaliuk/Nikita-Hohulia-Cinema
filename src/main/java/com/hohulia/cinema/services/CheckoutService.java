package com.hohulia.cinema.services;

import com.hohulia.cinema.exceptions.ServiceException;

public class CheckoutService {
    public double[][] getSelectedSeats(String[] sSelectedSeats) throws ServiceException {
        double[][] selectedSeat = null;

        if (sSelectedSeats != null && sSelectedSeats.length > 0) {
            selectedSeat = new double[sSelectedSeats.length][3];

            for (int i = 0; i < sSelectedSeats.length; i++) {
                String[] seatComponents = sSelectedSeats[i].split("-");

                selectedSeat[i][0] = Double.parseDouble(seatComponents[0]);
                selectedSeat[i][1] = Double.parseDouble(seatComponents[1]);
                selectedSeat[i][2] = Double.parseDouble(seatComponents[2]);

            }
        } else {
            throw new ServiceException("No selected seats found");
        }

        return selectedSeat;
    }

    public double getSelectedSeatsAmount(double[][] sSelectedSeats) throws ServiceException {
        double amount = 0;
        for (double[] sSelectedSeat : sSelectedSeats) amount += sSelectedSeat[2];
        return amount;
    }

}

