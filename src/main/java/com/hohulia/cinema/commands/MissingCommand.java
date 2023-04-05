package com.hohulia.cinema.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MissingCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/error.jsp";
    }
}

