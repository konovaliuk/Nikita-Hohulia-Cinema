package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand{

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            return "/WEB-INF/login.jsp";
        }
        else {
            try {
                User currentUser = userService.signInUser(request.getParameter("email"), request.getParameter("password"));
                int role = userService.getRole(currentUser);
                request.getSession().setAttribute("currentUser", currentUser);
                request.getSession().setAttribute("userRole", role);
                return "!/home";
            }
            catch (ServiceException e) {
                request.setAttribute("error", e.getMessage());
                return "/WEB-INF/login.jsp";
            }
        }
    }
}
