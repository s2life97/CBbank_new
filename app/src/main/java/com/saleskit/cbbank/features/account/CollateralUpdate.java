package com.saleskit.cbbank.features.account;

public class CollateralUpdate {

    /**
     * CustomerCollateralId : 0
     * CustomerProfileId : 0
     * CollateralType : 0
     * CollateralValue : 0
     * RateOfLending : 0
     */

    private int CustomerCollateralId;
    private int CustomerProfileId;
    private int CollateralType;
    private int CollateralValue;
    private int RateOfLending;

    public int getCustomerCollateralId() {
        return CustomerCollateralId;
    }

    public void setCustomerCollateralId(int CustomerCollateralId) {
        this.CustomerCollateralId = CustomerCollateralId;
    }

    public int getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(int CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public int getCollateralType() {
        return CollateralType;
    }

    public void setCollateralType(int CollateralType) {
        this.CollateralType = CollateralType;
    }

    public int getCollateralValue() {
        return CollateralValue;
    }

    public void setCollateralValue(int CollateralValue) {
        this.CollateralValue = CollateralValue;
    }

    public int getRateOfLending() {
        return RateOfLending;
    }

    public void setRateOfLending(int RateOfLending) {
        this.RateOfLending = RateOfLending;
    }
}
