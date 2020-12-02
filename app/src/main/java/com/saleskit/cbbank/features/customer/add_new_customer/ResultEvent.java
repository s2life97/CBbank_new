package com.saleskit.cbbank.features.customer.add_new_customer;

public class ResultEvent {
    public String result;
    public int assertStatus;
    public int education;
    public String dateOfBirth;
    public String name;
    public String productId;
    public String customerId;

    public ResultEvent(String result, int assertStatus, int education, String dateOfBirth, String name, String productId, String customerId) {
        this.result = result;
        this.assertStatus = assertStatus;
        this.education = education;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.productId = productId;
        this.customerId = customerId;
    }
}
