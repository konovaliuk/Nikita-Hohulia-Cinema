package com.hohulia.cinema.services;


public class ServiceFactory {
    private static final UserService userService = new UserService();
    private static final MovieService movieService = new MovieService();
    private static final ScheduleService scheduleService = new ScheduleService();
    private static final BookingService bookingService = new BookingService();
    private static final CheckoutService checkoutService = new CheckoutService();
    private static final PaymentService paymentService = new PaymentService();


    public static MovieService getMovieService() { return movieService; }
    public static UserService getUserService() { return userService; }
    public static ScheduleService getScheduleService() { return scheduleService; }
    public static BookingService getBookingService() { return bookingService;}
    public static CheckoutService getCheckoutService() { return checkoutService;}
    public static PaymentService getPaymentService() { return paymentService;}


}
