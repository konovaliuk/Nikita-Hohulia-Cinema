package com.hohulia.cinema.controller;

import com.hohulia.cinema.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {
    private static ControllerHelper instance;
    private static HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("/Cinema/home", new MainPageCommand());
        commands.put("/Cinema/error", new MissingCommand());
        commands.put("/Cinema/login", new LoginCommand());
        commands.put("/Cinema/logout", new LogoutCommand());
        commands.put("/Cinema/movies", new MoviesPageCommand());
        commands.put("/Cinema/signup", new SignUpCommand());
        commands.put("/Cinema/show", new ShowCommand());
        commands.put("/Cinema/booking", new BookingCommand());
        commands.put("/Cinema/checkout", new CheckoutCommand());
        commands.put("/Cinema/authorize_payment", new AuthorizePaymentCommand());
        commands.put("/Cinema/execute_payment", new ExecutePaymentCommand());
        commands.put("/Cinema/review_payment", new ReviewPaymentCommand());
        commands.put("/Cinema/my-tickets", new MyTicketsCommand());
    }

    public ICommand getCommand(HttpServletRequest req) {
        System.out.println("req.getRequestURI() = " + req.getRequestURI() );
        if(!commands.containsKey(req.getRequestURI())) {
            return new MissingCommand();
        }
        return commands.get(req.getRequestURI());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
