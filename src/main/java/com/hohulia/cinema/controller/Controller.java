package com.hohulia.cinema.controller;

import com.hohulia.cinema.commands.ICommand;
import com.hohulia.cinema.utilities.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet(name="Controller", urlPatterns = {"/home", "/signup", "/login", "/error", "/logout", "/movies", "/show", "/booking", "/checkout", "/authorize_payment", "/execute_payment"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        processRequest(request, response);

    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Controller starts ...");
        ICommand command = ControllerHelper.getInstance().getCommand(request);
        System.out.println("Command name: " + command.toString());
        String page = command.execute(request, response);

        if (page.startsWith("!")) {
            response.sendRedirect(page.substring(2));
        }
        else if (page.startsWith("https://www.sandbox.paypal.com/") || page.startsWith("https://www.paypal.com/") ){
            response.sendRedirect(page);
        }
        else {
            request.getServletContext().getRequestDispatcher(page).forward(request, response);
        }
    }

}

