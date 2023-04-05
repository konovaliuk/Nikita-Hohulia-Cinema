package com.hohulia.cinema.commands;

import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.CheckoutService;
import com.hohulia.cinema.services.MovieService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CheckoutCommand implements ICommand {
    private final CheckoutService checkoutService = ServiceFactory.getCheckoutService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //If authorized
        if (!RequestUtils.authorized(request))
            return "!/login";

        try {
            String[] sSelectedSeats = request.getParameterValues("selectedSeats"); //row-number-price
            double[][] selectedSeat = checkoutService.getSelectedSeats(sSelectedSeats);
            double amount = checkoutService.getSelectedSeatsAmount(selectedSeat);

            request.getSession().setAttribute("seatsSelected", selectedSeat);
            request.getSession().setAttribute("seatsString", selectedSeat.length);
            request.getSession().setAttribute("bookingShowId", Long.parseLong(request.getParameter("showId")));
            request.getSession().setAttribute("amount", amount);

        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            return "!/home";
        }

        return "/WEB-INF/checkout.jsp";

    }
}

