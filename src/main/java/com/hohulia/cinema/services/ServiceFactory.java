package com.hohulia.cinema.services;


public class ServiceFactory {
    private static final UserService userService = new UserService();
    private static final MovieService movieService = new MovieService();
    private static final ScheduleService scheduleService = new ScheduleService();
    private static final BookingService bookingService = new BookingService();

    public static MovieService getMovieService() { return movieService; }
    public static UserService getUserService() { return userService; }
    public static ScheduleService getScheduleService() { return scheduleService; }
    public static BookingService getBookingService() { return bookingService;}

}
