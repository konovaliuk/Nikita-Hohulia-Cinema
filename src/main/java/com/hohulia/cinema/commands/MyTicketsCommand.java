package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.Booking;
import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.BookingService;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.utilities.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.hohulia.cinema.Constants.DEFAULT_COLUMNS;
import static com.hohulia.cinema.Constants.DEFAULT_ROWS;

public class MyTicketsCommand implements ICommand{
    private final BookingService bookingService = ServiceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            if (!RequestUtils.authorized(request))
                return "!/login";

            User user = (User)request.getSession().getAttribute("currentUser");

            List <Booking> bookings = bookingService.getBookingsByUserId(user.getUserId());
            List<List<Seat>> seats = bookingService.getSeatsByBooking(bookings);

            request.setAttribute("bookings", bookings);
            request.setAttribute("seats", seats);
            return "/WEB-INF/my-tickets.jsp";
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            return "!/login";
        }
    }
}
