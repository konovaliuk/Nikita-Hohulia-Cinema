package com.hohulia.cinema.commands;

import com.hohulia.cinema.services.PaymentService;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecutePaymentCommand implements ICommand{
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        System.out.println("PaymentId: "+ paymentId + "  PayerId: "+ payerId);
        try {
            PaymentService paymentServices = new PaymentService();
            Payment payment = paymentServices.executePayment(paymentId, payerId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            return "receipt.jsp";

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "/pError";
        }
    }
}
