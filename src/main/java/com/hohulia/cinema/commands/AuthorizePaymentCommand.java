package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.services.PaymentService;
import com.hohulia.cinema.services.models.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
public class AuthorizePaymentCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String product = request.getParameter("product");
            String subtotal = request.getParameter("subtotal");
            String shipping = request.getParameter("shipping");
            String tax = request.getParameter("tax");
            String total = request.getParameter("total");
            OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);

            PaymentService paymentServices = new PaymentService();
            User user = (User)request.getSession().getAttribute("currentUser");

            return paymentServices.authorizePayment(orderDetail, user);

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "/WEB-INF/pError.jsp";
        } catch (Exception ex) {
            return "!/error";
        }
    }
}
