package com.hohulia.cinema.commands;

import com.hohulia.cinema.utilities.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CheckoutCommand implements ICommand {


    private static final String MAIN = "/index.jsp";
    private static final String ERROR = "!/error";
    private static final String LOGIN = "!/login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Buy command");
        System.out.println(request.getMethod());


        //If authorized
        if (!RequestUtils.authorized(request))
            return LOGIN;

        //put our showId, amount, and seats to session
        //currentUser(User) and userRole(int) is stored in the session
        String[] sSelectedSeats = request.getParameterValues("selectedSeats"); //row-number-price
        double[][] selectedSeat = null;
        double amount = 0;

        if (sSelectedSeats != null && sSelectedSeats.length > 0) {
            selectedSeat = new double[sSelectedSeats.length][3];

            for (int i = 0; i < sSelectedSeats.length; i++) {
                String[] seatComponents = sSelectedSeats[i].split("-");

                if (seatComponents.length == 3) {
                    selectedSeat[i][0] = Double.parseDouble(seatComponents[0]);
                    selectedSeat[i][1] = Double.parseDouble(seatComponents[1]);
                    selectedSeat[i][2] = Double.parseDouble(seatComponents[2]);
                    amount += selectedSeat[i][2];
                } else {
                    System.err.println("Invalid format for selected seat: " + sSelectedSeats[i]);
                    return ERROR;
                }
            }
        } else {
            System.err.println("No selected seats found");
            return MAIN;
        }

        System.out.println(Arrays.deepToString(selectedSeat));

        try {
            request.getSession().setAttribute("seatsSelected", selectedSeat);
            request.getSession().setAttribute("seatsString", selectedSeat.length);
            request.getSession().setAttribute("bookingShowId", Long.parseLong(request.getParameter("showId")));
            request.getSession().setAttribute("amount", amount);
        } catch (Exception e) {
            return MAIN;
        }

        return "/checkout.jsp";

    }
}

