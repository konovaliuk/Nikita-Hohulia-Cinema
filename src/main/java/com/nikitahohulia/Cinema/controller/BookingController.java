package com.nikitahohulia.Cinema.controller;

import com.nikitahohulia.Cinema.services.BookingService;
import com.nikitahohulia.Cinema.wrappers.BookingWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;

    @GetMapping("/booking")
    public String getMoviesForPage(@RequestParam(value = "showId", required = true) Long showId, Model theModel) {
        BookingWrapper bookingWrapper = bookingService.seatsByShow(showId);
        theModel.addAttribute("bookingWrapper", bookingWrapper);
        theModel.addAttribute("selectedSeats", new ArrayList<String>());
        return "booking";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam(value = "selectedSeats", required = false) List<String> selectedSeats, Model theModel) {
        System.out.println(selectedSeats);
        theModel.addAttribute("selectedSeats", selectedSeats);
        return "checkout";
    }

}
