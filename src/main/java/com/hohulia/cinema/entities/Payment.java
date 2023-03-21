package com.hohulia.cinema.entities;

import java.util.Objects;

public class Payment
{
    private long paymentId;
    private long bookingId;
    private int amount;
    private int status;

    public Payment(){}
    public Payment(long BookingId,int Amount,int Status)
    {
        this.bookingId = BookingId;
        this.amount = Amount;
        this.status = Status;
    }
    public Payment(long PaymentId,long BookingId,int Amount,int Status)
    {
        this.paymentId = PaymentId;
        this.bookingId = BookingId;
        this.amount = Amount;
        this.status = Status;
    }

    public long getPaymentId()
    {
        return paymentId;
    }
    public void setPaymentId(long value)
    {
        paymentId = value;
    }
    public long getBookingId()
    {
        return bookingId;
    }
    public void setBookingId(long value)
    {
        bookingId = value;
    }
    public int getAmount()
    {
        return amount;
    }
    public void setAmount(int value)
    {
        amount = value;
    }
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int value)
    {
        status = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getPaymentId() == payment.getPaymentId() && getBookingId() == payment.getBookingId() && getAmount() == payment.getAmount() && getStatus() == payment.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentId(), getBookingId(), getAmount(), getStatus());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
