package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.services.PaymentService;
import com.hohulia.cinema.services.models.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
public class AuthorizePaymentCommand implements ICommand{

    private static final String MAIN = "/index.jsp";
    private static final String ERROR = "/pError.jsp";
    private static final String LOGIN = "!/login";
    private static final String PAYMENT_FORM = "!/buy-tickets";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String product = request.getParameter("product");
        String subtotal = request.getParameter("subtotal");
        String shipping = request.getParameter("shipping");
        String tax = request.getParameter("tax");
        String total = request.getParameter("total");
        OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
        System.out.println(orderDetail.toString());

        try {
            PaymentService paymentServices = new PaymentService();
            System.out.println("Checkpoint 1");
            User user = (User)request.getSession().getAttribute("currentUser");
            String approvalLink = paymentServices.authorizePayment(orderDetail, user);
            System.out.println("Checkpoint 2");

            System.out.println(approvalLink);
            return approvalLink;

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return ERROR;
        }
    }
}
