package com.hohulia.cinema.services.models;

public class OrderDetail {
    private String productName;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;

    public OrderDetail(String productName, String subtotal,
                       String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProductName() {
        return productName;
    }

    public String getSubtotal() {
        return String.format("%.2f", subtotal).replace(",", ".");
    }

    public String getShipping() {
        return String.format("%.2f", shipping).replace(",", ".");
    }

    public String getTax() {
        return String.format("%.2f", tax).replace(",", ".");
    }

    public String getTotal() {
        return String.format("%.2f", total).replace(",", ".");
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "productName='" + getProductName() + '\'' +
                ", subtotal=" + getSubtotal() +
                ", shipping=" + getShipping() +
                ", tax=" + getTax() +
                ", total=" + getTotal() +
                '}';
    }
}
