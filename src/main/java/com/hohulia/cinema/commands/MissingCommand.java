package com.hohulia.cinema.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MissingCommand implements ICommand{
    private final static String ERROR = "/error.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ERROR;
    }
}

