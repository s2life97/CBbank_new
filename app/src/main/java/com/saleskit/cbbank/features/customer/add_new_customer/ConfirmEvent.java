package com.saleskit.cbbank.features.customer.add_new_customer;

public class ConfirmEvent {
    public String customerID;
    public double value;

    public ConfirmEvent(String customerID, double value) {
        this.customerID = customerID;
        this.value = value;
    }
}
