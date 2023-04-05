package com.hohulia.cinema.commands;

import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.BookingService;
import com.hohulia.cinema.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hohulia.cinema.Constants.DEFAULT_COLUMNS;
import static com.hohulia.cinema.Constants.DEFAULT_ROWS;


public class BookingCommand implements ICommand {

    private final BookingService bookingService = ServiceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String sShowId = request.getParameter("showId");
            long  showId = Integer.parseInt(sShowId);
            int[][][] seats= bookingService.getSeatsByShow(showId, DEFAULT_ROWS, DEFAULT_COLUMNS);

            request.setAttribute("showId", showId);
            request.setAttribute("seats", seats);
            return "/WEB-INF/booking.jsp";
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            return "!/login";
        }
    }

}
