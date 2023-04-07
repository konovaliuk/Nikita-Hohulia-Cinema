package com.hohulia.cinema.commands;

import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.BookingService;
import com.hohulia.cinema.services.PaymentService;
import com.hohulia.cinema.services.ServiceFactory;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ExecutePaymentCommand implements ICommand{
    PaymentService paymentServices = ServiceFactory.getPaymentService();
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");
            User user = (User)request.getSession().getAttribute("currentUser");
            double[][] selectedSeat = (double[][])request.getSession().getAttribute("seatsSelected");
            long bookingShowId = (long)request.getSession().getAttribute("bookingShowId");

            Payment payment = paymentServices.executePayment(paymentId, payerId, user, selectedSeat, bookingShowId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            return "/WEB-INF/receipt.jsp";

        } catch (ServiceException | PayPalRESTException |SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "/WEB-INF/pError.jsp";
        }
    }
}
