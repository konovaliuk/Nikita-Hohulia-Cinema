package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.CredentialsException;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.ServiceFactory;
import com.hohulia.cinema.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements ICommand{

    private final UserService userService = ServiceFactory.getUserService();

    private static final String MAIN = "/index.jsp";
    private static final String ERROR = "!/error";
    private static final String SINGUP = "/signup";
    private static final String LOGIN = "/login";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            return "/signup.jsp";
        }
        else {
            try {

                User currentUser = userService.singUpUser(request.getParameter("email"), request.getParameter("password"));
                int role = userService.getRole(currentUser);
                request.getSession().setAttribute("currentUser", currentUser);
                request.getSession().setAttribute("userRole", role);
                return "!/home";
            }
            catch (ServiceException | CredentialsException e) {
                request.setAttribute("error", e.getMessage());
                System.out.println(e.getMessage());
                return "/signup.jsp";
            }
        }
    }
}
