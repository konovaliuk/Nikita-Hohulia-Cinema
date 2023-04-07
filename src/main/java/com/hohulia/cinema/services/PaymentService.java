package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.BookingDaoImp;
import com.hohulia.cinema.dao.implementation.ScheduleDaoImp;
import com.hohulia.cinema.dao.implementation.SeatDaoImp;
import com.hohulia.cinema.dao.interfaces.BookingInterface;
import com.hohulia.cinema.dao.interfaces.ScheduleInterface;
import com.hohulia.cinema.dao.interfaces.SeatInterface;
import com.hohulia.cinema.entities.Booking;
import com.hohulia.cinema.entities.Schedule;
import com.hohulia.cinema.entities.Seat;
import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.services.models.OrderDetail;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final BookingService bookingService = ServiceFactory.getBookingService();
    private static final String CLIENT_ID = "AXBOAw5hW7c1a4EUJeB2RicnOTC6uLwyoQ-bWCMseyC_dh15RE5bM6jLwhkoMKCSNLpKpryFobOmd9jj";
    private static final String CLIENT_SECRET = "EHxqmmOYToRa61_Ot2nRktzTqLwxPFkHSm0Did5LNk2_9fqE4flVuII7sErxrt3J27c2pUXcvaxIsAT5";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail, User user)
            throws PayPalRESTException {

        Payer payer = getPayerInformation(user);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation(User user) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setEmail(user.getEmail());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:9090/Cinema/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:9090/Cinema/review_payment");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getProductName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName(orderDetail.getProductName());
        item.setPrice(orderDetail.getSubtotal());
        item.setTax(orderDetail.getTax());
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId, User user, double[][] selectedSeat, long bookingShowId)
            throws PayPalRESTException, SQLException, ServiceException {

        com.hohulia.cinema.dao.interfaces.SqlTransaction transaction = null;
        Payment ret = null;

        try (Connection conn = DBCPDataSource.getConnection()) {
            //Transaction
            BookingInterface bookingDao = new BookingDaoImp(conn);
            SeatInterface seatDao= new SeatDaoImp(conn);
            transaction = new com.hohulia.cinema.dao.interfaces.SqlTransaction(conn);
            transaction.beginTransaction();
            //Get show(startTime)
            ScheduleInterface scheduleDao = new ScheduleDaoImp(conn);
            Schedule show = scheduleDao.findById(bookingShowId);
            //Add booking and and get booking_id
            Booking booking = new Booking(show.getStartTime(), user.getUserId(), bookingShowId);
            booking = bookingDao.makeBooking(booking);
            //Add seats
            List<Seat> seats = new ArrayList<>();
            for (int i = 0; i < selectedSeat.length; i++)
                seats.add(new Seat((int) selectedSeat[i][0], (int) selectedSeat[i][1], booking.getBookingId(), (int) selectedSeat[i][2], bookingShowId));
            seatDao.addSeat(seats);

            //Execute payment
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment payment = new Payment().setId(paymentId);

            APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

            ret = payment.execute(apiContext, paymentExecution);
            transaction.endTransaction();
            return ret;
        } catch (SQLException e) {
            transaction.rollbackTransaction();
            throw new ServiceException("Error executing payment(Your seats may already be selected)");
        }
    }
}