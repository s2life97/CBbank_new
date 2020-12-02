package com.saleskit.cbbank.features.kpi;

public class CustomerModel {
    private String expireDate;
    private String companyName;
    private String information;
    private double moneyBorrowed;

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getMoneyBorrowed() {
        return moneyBorrowed;
    }

    public void setMoneyBorrowed(double moneyBorrowed) {
        this.moneyBorrowed = moneyBorrowed;
    }
}
