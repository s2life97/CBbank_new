package com.saleskit.cbbank.features.customer.add_new_customer;

public class FinalEvent {
    public String date;
    public String name;
    public String money;
    public String time;
    public String customerProfileId;
    public FinalEvent(String date, String name, String money, String time, String customerProfileId) {
        this.date = date;
        this.name = name;
        this.money = money;
        this.time = time;
        this.customerProfileId = customerProfileId;
    }
}
