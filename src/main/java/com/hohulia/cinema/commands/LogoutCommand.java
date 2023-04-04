package com.hohulia.cinema.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ICommand{
    private final static String SIGNIN = "!/login";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        System.out.println("Invalidate");
        return SIGNIN;
    }
}