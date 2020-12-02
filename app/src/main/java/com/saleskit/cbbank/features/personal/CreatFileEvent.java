package com.saleskit.cbbank.features.personal;

public class CreatFileEvent {
    public CustomerInfo.DataBean customer;

    public CreatFileEvent(CustomerInfo.DataBean customer) {
        this.customer = customer;
    }
}
