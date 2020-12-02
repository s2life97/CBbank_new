package com.saleskit.cbbank.features.account;

public class AllowInsert {

    /**
     * CustomerProfileAllowcationId : 0
     * CustomerProfileId : 0
     * UserId : string
     */

    private int CustomerProfileAllowcationId;
    private int CustomerProfileId;
    private String UserId;

    public AllowInsert(int customerProfileAllowcationId, int customerProfileId, String userId) {
        CustomerProfileAllowcationId = customerProfileAllowcationId;
        CustomerProfileId = customerProfileId;
        UserId = userId;
    }

    public int getCustomerProfileAllowcationId() {
        return CustomerProfileAllowcationId;
    }

    public void setCustomerProfileAllowcationId(int CustomerProfileAllowcationId) {
        this.CustomerProfileAllowcationId = CustomerProfileAllowcationId;
    }

    public int getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(int CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
}
