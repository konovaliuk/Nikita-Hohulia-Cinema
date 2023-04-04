package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.BookingService;
import com.hohulia.cinema.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static com.hohulia.cinema.Constants.DEFAULT_PRICE;

public class BookingCommand implements ICommand {

    private final BookingService bookingService = ServiceFactory.getBookingService();

    private static final String ERROR = "!/error";//starts with ! means restart
    private static final String LOGIN = "/login"; // If want to buy tickets

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long showId;
        try {
            String sShowId = request.getParameter("showId");

            try{
                showId = Integer.parseInt(sShowId);

            } catch (Exception e){
                return ERROR;
            }


            List<Seat> seatsList = bookingService.getSeatsByShow(showId);

            int numRows = 6; //  I have just one hall_id --> if not them hallDaoImp.getHallById ant than parse my rows and number
            int numCols = 6;
            for (Seat seat : seatsList) {
                numRows = Math.max(numRows, seat.getRow());
                numCols = Math.max(numCols, seat.getNumber());
            }

            int[][][] seatArray = new int[numRows][numCols][2];//2- price

            for (Seat seat : seatsList) {
                seatArray[seat.getRow() - 1][seat.getNumber() - 1][0] = 1; // seat is reserved
                seatArray[seat.getRow() - 1][seat.getNumber() - 1][1] = seat.getSeatPrice();
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    System.out.print(seatArray[i][j][0] + " ");
                    if(seatArray[i][j][0]==0)
                        seatArray[i][j][1] = DEFAULT_PRICE;
                }
                System.out.println();
            }

            request.setAttribute("showId", showId);
            request.setAttribute("seats", seatArray);
            return "/booking.jsp";
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            return "/login.jsp";
        }
    }

}
